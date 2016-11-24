package com.piecakepie.animeinfo.detail;

import com.piecakepie.animeinfo.base.BasePresenter;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.util.DataUtil;

public class DetailPresenter extends BasePresenter<DetailContract.View> implements DetailContract.Presenter {

   public void getAnimeDataById(int id) {
      AnimeData data = DataUtil.getAnimeDataById(id);
      if (data != null) {
         loadContent(data);
      } else {
         getView().onNullData();
      }
   }

   private void loadContent(AnimeData data) {
      getView().setImage(data.getImageUrl());
      getView().setTitle(data.getTitle());
      getView().setProduction(data.getProduction());
      getView().setSummary(data.getPlotSummary());
      getView().setTableData(data);
   }
}
