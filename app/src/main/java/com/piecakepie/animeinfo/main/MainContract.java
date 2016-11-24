package com.piecakepie.animeinfo.main;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.piecakepie.animeinfo.base.BaseView;
import com.piecakepie.animeinfo.model.AnimeData;

import java.util.List;

public interface MainContract {
   interface View extends BaseView {
      void showContent();
      void setData(List<AnimeData> animeData);
      void showError(Throwable t);
   }

   interface Presenter extends MvpPresenter<View> {
      void pullData(List<String> animeIdList, final boolean pullToRefresh);
   }
}
