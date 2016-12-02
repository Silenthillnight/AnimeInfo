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
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.Collections;
import java.util.List;

class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

   private SharedPreferences sharedPreferences;

   MainPresenter() {
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
         service.getAnimeList(createQueryString(animeIdList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Ann>() {
            @Override
            public void call(Ann ann) {
               animeData.clear();
               parseData(animeData, ann);
            }
         }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
               Log.e("AnimeInfo", "Error while querying animeNewsNetwork: " + throwable);
               getView().showError(throwable);
            }
         });
      } else {
         Log.d("AnimeInfo", "Got data from cache!");
         showContent(animeData);
      }
   }

   private void parseData(final List<AnimeData> animeData, final Ann ann) {
      final List<Anime> animeList = ann.getAnime();
      Collections.sort(animeList);

      Observable.from(animeList)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Anime>() {
                   @Override
                   public void onCompleted() {
                      storeAnimeData(animeData);
                      storeLocalCacheData(animeData);
                      getView().showContent();
                   }

                   @Override
                   public void onError(Throwable e) {
                      Log.e("AnimeInfo", "Error while trying to parse: " + e);
                   }

                   @Override
                   public void onNext(Anime anime) {
                      Log.d("AnimeInfo", "Parsing anime: " + anime.getName());
                      AnimeData data = DataUtil.convertAnimeToAnimeData(anime);
                      animeData.add(data);
                      getView().addData(data);
                   }
                });
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
