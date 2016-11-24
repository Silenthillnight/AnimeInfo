package com.piecakepie.animeinfo.detail;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.piecakepie.animeinfo.base.BaseView;
import com.piecakepie.animeinfo.model.AnimeData;

public interface DetailContract {
   interface View extends BaseView {
      void setImage(String imageUrl);
      void setTitle(String title);
      void setProduction(String company);
      void setSummary(String summary);
      void setTableData(AnimeData data);
      void onNullData();
   }

   interface Presenter extends MvpPresenter<View> {
      void getAnimeDataById(int id);
   }
}
