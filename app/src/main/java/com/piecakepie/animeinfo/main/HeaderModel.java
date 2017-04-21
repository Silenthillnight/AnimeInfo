package com.piecakepie.animeinfo.main;

import android.support.annotation.DrawableRes;
import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.piecakepie.animeinfo.R;
import com.piecakepie.animeinfo.ui.HeaderView;

@EpoxyModelClass(layout = R.layout.model_header)
public abstract class HeaderModel extends EpoxyModel<HeaderView> {

   @EpoxyAttribute
   @DrawableRes
   int image;

   @EpoxyAttribute
   String title;

   @EpoxyAttribute
   String subtitle;

   @Override
   public void bind(HeaderView view) {
      view.setImage(image);
      view.setTitle(title);
      view.setSubtitle(subtitle);
   }
}
