package com.piecakepie.animeinfo.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.piecakepie.animeinfo.base.BasePresenter;
import com.piecakepie.animeinfo.dto.realm.RealmAnimeData;
import com.piecakepie.animeinfo.manager.AnimeNewsNetworkManager;
import com.piecakepie.animeinfo.dto.network.Anime;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.dto.network.Ann;
import com.piecakepie.animeinfo.service.AnimeNewsNetworkService;
import com.piecakepie.animeinfo.util.DataUtil;
import com.piecakepie.animeinfo.util.RealmUtil;
import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
   public void pullData(List<String> animeIdList, final String seasonName, final boolean pullToRefresh) {
      final List<AnimeData> animeData = DataUtil.getAnimeData(sharedPreferences);

      if (animeData.isEmpty() || pullToRefresh) {
         getAnimeList(animeData, seasonName, animeIdList);
      } else {
         Log.d("AnimeInfo", "Got data from cache!");
         showContent(animeData);
      }
   }

   private void getAnimeList(final List<AnimeData> animeData, final String seasonName, List<String> animeIdList) {
      AnimeNewsNetworkService service = AnimeNewsNetworkManager.createService(AnimeNewsNetworkService.class);

      service.getAnimeList(createQueryString(animeIdList))
             // get anime list from response
             .flatMapIterable(new Func1<Ann, Iterable<Anime>>() {
                @Override
                public Iterable<Anime> call(Ann ann) {
                   return ann.getAnime();
                }
             })
             // sort anime list
             .toSortedList()
             // get observable of each anime element
             .flatMap(new Func1<List<Anime>, Observable<Anime>>() {
                @Override
                public Observable<Anime> call(List<Anime> animes) {
                   return Observable.from(animes);
                }
             })
             .subscribeOn(Schedulers.computation())
             .observeOn(AndroidSchedulers.mainThread())
             // parse each element into an RealmAnimeData object and send to view
             .subscribe(new Subscriber<Anime>() {
                @Override
                public void onCompleted() {
                   storeAnimeData(animeData);
                   storeLocalCacheData(animeData);
                   getView().showContent();
                }

                @Override
                public void onError(Throwable e) {
                   Log.e("AnimeInfo", "Error while trying to parse.", e);
                }

                @Override
                public void onNext(Anime anime) {
                   Log.d("AnimeInfo", "Parsing anime: " + anime.getName());
                   AnimeData data = DataUtil.convertToAnimeData(anime, seasonName);
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
      persistAnimeData(animeData);
   }

   private void storeLocalCacheData(List<AnimeData> animeData) {
      DataUtil.populateLocalCache(animeData);
   }

   private void persistAnimeData(List<AnimeData> animeData) {
      Observable.from(animeData)
                .map(new Func1<AnimeData, RealmAnimeData>() {
                   @Override
                   public RealmAnimeData call(AnimeData animeData) {
                      return RealmUtil.convertToRealmAnimeData(animeData);
                   }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<RealmAnimeData>>() {
                   @Override
                   public void onCompleted() {
                      Log.d("AnimeInfo", "Committed Realm data.");
                   }

                   @Override
                   public void onError(Throwable e) {
                      Log.d("AnimeInfo", "Error while trying to commit Realm data.");
                   }

                   @Override
                   public void onNext(List<RealmAnimeData> realmAnimeDatas) {
                      Realm.init(getContext());
                      final Realm realm = Realm.getDefaultInstance();
                      realm.beginTransaction();
                      realm.insertOrUpdate(realmAnimeDatas);
                      realm.commitTransaction();
                   }
                });
   }

   /**
    * Concatenates anime ids with a '/' inbetween each id because ANN has a terrible query system
    * @return  concatenated query string
    */
   private String createQueryString(List<String> animeIdList) {
      return TextUtils.join("/", animeIdList);
   }
}
