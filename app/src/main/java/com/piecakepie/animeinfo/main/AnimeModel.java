package com.piecakepie.animeinfo.main;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.OnModelClickListener;
import com.piecakepie.animeinfo.R;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.ui.AnimeView;


public class AnimeModel extends EpoxyModel<AnimeView> {

   private final AnimeData animeData;

   @EpoxyAttribute
   boolean selected;

   @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
   View.OnClickListener listener;

   public AnimeModel(AnimeData animeData) {
      this.animeData = animeData;
      id(animeData.getId());
   }

   @Override
   protected int getDefaultLayout() {
      return R.layout.model_anime;
   }

   @Override
   public void bind(AnimeView view) {
      view.setImage(animeData.getImageUrl());
      view.setTitle(animeData.getTitle());
      view.setSubtitle(animeData.getProduction());
      view.setDetail(animeData.getPlotSummary());
      view.setSelected(selected);
      view.showDetail(animeData.isShowDetail());
      view.setOnClickListener(listener);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      if (!super.equals(o)) return false;

      AnimeModel that = (AnimeModel) o;

      return animeData != null ? animeData.equals(that.animeData) : that.animeData == null;

   }

   @Override
   public int hashCode() {
      int result = super.hashCode();
      result = 31 * result + (animeData != null ? animeData.hashCode() : 0);
      return result;
   }
}
