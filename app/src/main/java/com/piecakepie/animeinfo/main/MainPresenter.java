package com.piecakepie.animeinfo.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.piecakepie.animeinfo.base.BasePresenter;
import com.piecakepie.animeinfo.manager.AnimeNewsNetworkManager;
import com.piecakepie.animeinfo.dto.Anime;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.dto.Ann;
import com.piecakepie.animeinfo.service.AnimeNewsNetworkService;
import com.piecakepie.animeinfo.util.DataUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.HttpURLConnection;
import java.util.List;

class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

   private SharedPreferences sharedPreferences;

   public MainPresenter() {
      super();
      this.sharedPreferences = getContext().getSharedPreferences(DataUtil.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
   }

   /**
    * This will pull the data down from ANN.  Eventually, I want to be able to store the data into a local database.
    * That way, the user no longer has to wait for the queries to come back every time.  After the initial pull,
    * the app should be able to work offline.  The user can force a refresh from pull down.
    */
   public void pullData(List<String> animeIdList, final boolean pullToRefresh) {
      AnimeNewsNetworkService service = AnimeNewsNetworkManager.createService(AnimeNewsNetworkService.class);
      final List<AnimeData> animeData = DataUtil.getAnimeData(sharedPreferences);

      if (animeData.isEmpty() || pullToRefresh) {
         service.getAnimeList(createQueryString(animeIdList)).enqueue(new Callback<Ann>() {
            @Override
            public void onResponse(Call<Ann> call, Response<Ann> response) {
               if (response.code() == HttpURLConnection.HTTP_OK) {
                  Log.d("AnimeInfo", "Got Data from the server!");
                  animeData.clear();
                  final Ann ann = response.body();
                  for (Anime anime : ann.getAnime()) {
                     animeData.add(DataUtil.convertAnimeToAnimeData(anime));
                  }

                  storeAnimeData(animeData);
                  showContent(animeData);
               } else {
                  Log.e("AnimeInfo", "Request error: " + response.message());
                  if (!animeData.isEmpty()) {
                     showContent(animeData);
                  } else {
                     getView().showError(null);
                  }
               }
            }

            @Override
            public void onFailure(Call<Ann> call, Throwable t) {
               Log.e("AnimeInfo", "Error while trying to query AnimeNewsNetwork: " + t.getMessage());
               Log.e("AnimeInfo", "Query: " + call.request().url());
               getView().showError(t);
            }
         });
      } else {
         Log.d("AnimeInfo", "Got data from cache!");
         showContent(animeData);
      }
   }

   /**
    * Tell view to show data on screen
    * @param animeData  current anime data
    */
   private void showContent(final List<AnimeData> animeData) {
      storeLocalCacheData(animeData);
      getView().setData(animeData);
      getView().showContent();
   }

   /**
    * Cache anime data into shared preferences so we don't have to query every time we open the app.
    * @param animeData  List of anime data queried from the server
    */
   private void storeAnimeData(List<AnimeData> animeData) {
      DataUtil.cacheAnimeData(sharedPreferences, animeData);
   }

   private void storeLocalCacheData(List<AnimeData> animeData) {
      DataUtil.populateLocalCache(animeData);
   }

   /**
    * Concatenates anime ids with a '/' inbetween each id because ANN has a terrible query system
    * @return  concatenated query string
    */
   private String createQueryString(List<String> animeIdList) {
      return TextUtils.join("/", animeIdList);
   }
}
