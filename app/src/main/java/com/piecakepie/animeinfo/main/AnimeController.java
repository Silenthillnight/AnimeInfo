package com.piecakepie.animeinfo.main;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;
import com.piecakepie.animeinfo.R;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.ui.AnimeView;

import java.util.List;
import java.util.Locale;

public class AnimeController extends TypedEpoxyController<List<AnimeData>> {

   public interface AdapterCallbacks {
      void onSummarySwitched(boolean switched);
      void onMoreInfoClicked(AnimeView view, int position);
   }

   private final AdapterCallbacks callbacks;

   @AutoModel
   HeaderModel_ header;

   @AutoModel
   SummaryModel_ summary;

   public AnimeController(AdapterCallbacks callbacks) {
      this.callbacks = callbacks;
   }

   @Override
   protected void buildModels(List<AnimeData> data) {
      header.image(R.drawable.header_image)
            .title("Fall 2016")
            .subtitle("All selected")
            .addTo(this);

      summary.title("Fall 2016")
             .subtitle(String.format(Locale.ENGLISH, "Over %d titles", data.size()))
             .status("Selected")
             .listener((buttonView, isChecked) -> callbacks.onSummarySwitched(isChecked))
             .addTo(this);

      // use (position - 2) because we always have a header and summary view above our dynamic models
      for (AnimeData animeData : data) {
         add(new AnimeModel_(animeData)
                   .selected(animeData.isSelected())
                   .listener((model,
                              parentView,
                              clickedView,
                              position) ->
                                   callbacks.onMoreInfoClicked(parentView, position - 2)));
      }
   }
}
