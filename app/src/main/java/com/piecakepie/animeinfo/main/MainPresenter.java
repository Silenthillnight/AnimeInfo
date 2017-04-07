package com.piecakepie.animeinfo.main;

import com.piecakepie.animeinfo.base.BasePresenter;
import com.piecakepie.animeinfo.manager.AnimeNewsNetworkManager;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.util.DataUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

   private AnimeNewsNetworkManager animeNewsNetworkManager;

   @Inject
   public MainPresenter(AnimeNewsNetworkManager animeNewsNetworkManager) {
      this.animeNewsNetworkManager = animeNewsNetworkManager;
   }

   /**
    * This will pull the data down from ANN.  Eventually, I want to be able to store the data into a local database.
    * That way, the user no longer has to wait for the queries to come back every time.  After the initial pull,
    * the app should be able to work offline.  The user can force a refresh from pull down.
    */
   public void pullData(List<String> animeIdList,
                        final String seasonName,
                        final boolean pullToRefresh) {
      getAnimeList(seasonName, animeIdList);
   }

   private void getAnimeList(final String seasonName, List<String> animeIdList) {
      animeNewsNetworkManager
            .getAnimeData(seasonName, animeIdList)
            .observeOn(AndroidSchedulers.mainThread())
            .toSortedList()
            .doOnSuccess(this::showContent)
            .subscribe();
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

   private void storeLocalCacheData(List<AnimeData> animeData) {
      DataUtil.populateLocalCache(animeData);
   }
}
