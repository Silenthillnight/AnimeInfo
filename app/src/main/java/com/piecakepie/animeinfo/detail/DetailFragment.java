package com.piecakepie.animeinfo.detail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import butterknife.BindView;
import com.piecakepie.animeinfo.R;
import com.piecakepie.animeinfo.base.BaseFragment;
import com.piecakepie.animeinfo.dto.Website;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.util.Constants;
import com.piecakepie.animeinfo.util.DetailEnum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailFragment
   extends BaseFragment<DetailContract.View, DetailContract.Presenter>
   implements DetailContract.View {

   @BindView(R.id.detail_image)
   ImageView detailImage;

   @BindView(R.id.detail_title)
   TextView detailTitle;

   @BindView(R.id.detail_production)
   TextView detailProduction;

   @BindView(R.id.detail_summary)
   TextView detailSummary;

   @BindView(R.id.detail_table)
   TableLayout tableLayout;

   @Override
   public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      init();
   }

   protected void init() {
      Log.d("AnimeInfo", "Called Init!");
      Bundle bundle = getArguments();
      if (bundle != null) {
         final int animeId = bundle.getInt(Constants.EXTRA_ANIME_ID, 0);
         if (animeId != 0) {
            presenter.getAnimeDataById(animeId);
         } else {
            finish();
         }
      } else {
         finish();
      }
   }

   @Override
   protected int getRootLayoutId() {
      return R.layout.fragment_detail;
   }

   @Override
   public DetailContract.Presenter createPresenter() {
      return new DetailPresenter();
   }

   public void setImage(String imageUrl) {
      if (imageUrl != null) {
         Picasso.with(getContext()).load(imageUrl).into(detailImage);
      } else {
         detailImage.setImageDrawable(getResources().getDrawable(R.drawable.no_image, null));
      }
   }

   public void setTitle(String title) {
      detailTitle.setText(title);
   }

   public void setProduction(String company) {
      detailProduction.setText(company);
   }

   public void setSummary(String summary) {
      if (summary == null) {
         summary = "No Summary.";
      }

      if (Build.VERSION.SDK_INT < 24) {
         detailSummary.setText(Html.fromHtml(summary));
      } else {
         detailSummary.setText(Html.fromHtml(summary, Html.FROM_HTML_MODE_LEGACY));
      }

   }

   /**
    * Setting all table cells manually since I don't care to do it dynamically right now
    * @param data  Anime data
    */
   public void setTableData(AnimeData data) {
      LayoutInflater inflater = getActivity().getLayoutInflater();
      // Alternate titles
      TableRow tableRow = (TableRow) inflater.inflate(R.layout.detail_table_row_single, tableLayout, false);
      populateTableSingleCell(data, DetailEnum.ALTERNATE_TITLES, tableRow, false);

      // Genres
      tableRow = (TableRow) inflater.inflate(R.layout.detail_table_row_single, tableLayout, false);
      populateTableSingleCell(data, DetailEnum.GENRES, tableRow, false);

      // Themes
      tableRow = (TableRow) inflater.inflate(R.layout.detail_table_row_single, tableLayout, false);
      populateTableSingleCell(data, DetailEnum.THEMES, tableRow, false);

      // Viewer Rating & Running Time
      tableRow = (TableRow) inflater.inflate(R.layout.detail_table_row_double, tableLayout, false);
      populateTableDoubleCell(data, DetailEnum.VIEWER_RATING, DetailEnum.RUNNING_TIME, tableRow);

      // Number of Episodes && Vintage
      tableRow = (TableRow) inflater.inflate(R.layout.detail_table_row_double, tableLayout, false);
      populateTableDoubleCell(data, DetailEnum.NUMBER_OF_EPISODES, DetailEnum.VINTAGE, tableRow);

      // Websites
      tableRow = (TableRow) inflater.inflate(R.layout.detail_table_row_single, tableLayout, false);
      populateTableSingleCell(data, DetailEnum.WEBSITES, tableRow, true);
   }

   protected void populateTableSingleCell(AnimeData data, DetailEnum cellEnum, TableRow tableRow, boolean isWebsite) {
      TextView cellHeader = (TextView) tableRow.findViewById(R.id.cell_header);
      TextView cellBody = (TextView) tableRow.findViewById(R.id.cell_body);

      cellHeader.setText(cellEnum.getHeader());
      if (isWebsite) {
         if (Build.VERSION.SDK_INT < 24) {
            cellBody.setText(Html.fromHtml(getBody(cellEnum, data)));
         } else {
            cellBody.setText(Html.fromHtml(getBody(cellEnum, data), Html.FROM_HTML_MODE_LEGACY));
         }

         cellBody.setMovementMethod(LinkMovementMethod.getInstance());
      } else {
         cellBody.setText(getBody(cellEnum, data));
      }

      tableLayout.addView(tableRow);
   }

   protected void populateTableDoubleCell(AnimeData data, DetailEnum cellOneEnum, DetailEnum cellTwoEnum, TableRow tableRow) {
      TextView cellOneHeader = (TextView) tableRow.findViewById(R.id.cell_one_header);
      TextView cellOneBody = (TextView) tableRow.findViewById(R.id.cell_one_body);
      TextView cellTwoHeader = (TextView) tableRow.findViewById(R.id.cell_two_header);
      TextView cellTwoBody = (TextView) tableRow.findViewById(R.id.cell_two_body);

      cellOneHeader.setText(cellOneEnum.getHeader());
      cellTwoHeader.setText(cellTwoEnum.getHeader());

      cellOneBody.setText(getBody(cellOneEnum, data));
      cellTwoBody.setText(getBody(cellTwoEnum, data));

      tableLayout.addView(tableRow);
   }

   protected String getBody(DetailEnum detailEnum, AnimeData data) {
      String body = "N/A";

      switch (detailEnum) {
         case ALTERNATE_TITLES:
            if (data.getAlternateTitles() != null) {
               body = formatStringList(data.getAlternateTitles(), true);
            }
            break;
         case TYPE:
            if (data.getType() != null) {
               body = data.getType();
            }
            break;
         case GENRES:
            if (data.getGenres() != null) {
               body = formatStringList(data.getGenres(), false);
            }
            break;
         case THEMES:
            if (data.getThemes() != null) {
               body = formatStringList(data.getThemes(), false);
            }
            break;
         case VIEWER_RATING:
            if (data.getViewerRating() != null) {
               body = data.getViewerRating();
            }
            break;
         case RUNNING_TIME:
            final String runningTime = data.getRunningTime();
            if (runningTime != null) {
               body = TextUtils.isDigitsOnly(runningTime) ? runningTime + " minutes" : runningTime;
            }
            break;
         case NUMBER_OF_EPISODES:
            if (data.getNumberOfEpisodes() != null) {
               body = data.getNumberOfEpisodes().toString();
            }
            break;
         case VINTAGE:
            if (data.getVintage() != null) {
               body = data.getVintage();
            }
            break;
         case WEBSITES:
            if (data.getWebsites() != null) {
               body = formatWebsiteList(data.getWebsites());
            }
            break;
      }

      return body;
   }

   protected String formatStringList(List<String> stringList, boolean lineBreak) {
      StringBuilder builder = new StringBuilder();

      for (int i = 0 ; i < stringList.size(); i++) {
         builder.append(stringList.get(i));

         if (i < stringList.size() - 1) {
            if (lineBreak) {
               builder.append("\n");
            } else {
               builder.append(", ");
            }

         }
      }

      return builder.toString();
   }

   protected String formatWebsiteList(List<Website> websiteList) {
      StringBuilder builder = new StringBuilder();

      for (int i = 0 ; i < websiteList.size(); i++) {
         Website website = websiteList.get(i);
         builder.append("<a href=\"")
                .append(website.getUrl())
                .append("\">")
                .append(website.getLink())
                .append("</a>");

         if (i < websiteList.size() - 1) {
            builder.append("<br><br>");
         }
      }

      return builder.toString();
   }

   public void onNullData() {
      finish();
   }

   protected void finish() {
      getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
   }
}
