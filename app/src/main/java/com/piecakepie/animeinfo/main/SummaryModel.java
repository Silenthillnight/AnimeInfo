package com.piecakepie.animeinfo.main;

import android.widget.Switch;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.piecakepie.animeinfo.R;
import com.piecakepie.animeinfo.ui.SummaryView;

@EpoxyModelClass(layout = R.layout.model_summary)
public abstract class SummaryModel extends EpoxyModel<SummaryView> {

   @EpoxyAttribute
   String title;

   @EpoxyAttribute
   String subtitle;

   @EpoxyAttribute
   String status;

   @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
   Switch.OnCheckedChangeListener listener;

   @Override
   public void bind(SummaryView view) {
      view.setTitle(title);
      view.setSubtitle(subtitle);
      view.setStatus(status);
      view.setSwitchOnClickListener(listener);
   }

   @Override
   public boolean shouldSaveViewState() {
      return true;
   }
}
