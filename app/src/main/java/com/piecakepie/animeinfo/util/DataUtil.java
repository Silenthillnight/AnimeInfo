package com.piecakepie.animeinfo.util;

import android.content.SharedPreferences;
import android.util.Log;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.piecakepie.animeinfo.dto.Anime;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.dto.Credit;
import com.piecakepie.animeinfo.dto.Img;
import com.piecakepie.animeinfo.dto.Info;
import com.piecakepie.animeinfo.dto.Website;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {

   public final static String SHARED_PREFERENCES_KEY = "anime_info_preferences";
   private final static String ANIME_DATA_KEY = "anime_data";
   private final static String THUMBNAIL_IDENTIFIER = "fit200x200";
   private final static String BIG_THUMBNAIL_IDENTIFIER = "max500x600";
   private final static String IMAGE_IDENTIFIER = "encyc";

   private static SparseArray<AnimeData> animeDataMap;

   public static AnimeData getAnimeDataById(int id) {
      return animeDataMap.get(id, null);
   }

   /**
    * Converts the anime DTO to a usable object for displaying data
    *
    * @param anime         DTO object from source
    * @return animeData    Object used for displaying information
    */
   public static AnimeData convertAnimeToAnimeData(Anime anime) {
      final AnimeData animeData = new AnimeData();
      animeData.setId(anime.getId());
      animeData.setTitle(anime.getName());
      animeData.setType(anime.getType());

      // Every anime should have a listing to the ann site
      final List<Website> websites = new ArrayList<>();
      websites.add(createAnimeNewsNetworkLink(anime.getId()));
      animeData.setWebsites(websites);

      for (Info info : anime.getInfo()) {
         switch (info.getType()) {
            case "Picture":
               for (Img img : info.getImg()) {
                  // Thumbnails from ANN come at 200px and below
                  if (img.getSrc().contains(THUMBNAIL_IDENTIFIER)) {
                     animeData.setThumbnailUrl(img.getSrc());
                     // Main images from ANN come at greater than 600px height wise
                  } else if (img.getSrc().contains(BIG_THUMBNAIL_IDENTIFIER)
                             && animeData.getImageUrl() != null
                             && animeData.getImageUrl().isEmpty()) {
                     animeData.setImageUrl(img.getSrc());
                  } else if (img.getSrc().contains(IMAGE_IDENTIFIER)) {
                     animeData.setImageUrl(img.getSrc());
                  }
               }
               break;
            case "Alternative title":
               if (animeData.getAlternateTitles() == null) {
                  final List<String> altTitles = new ArrayList<>();
                  altTitles.add(info.getValue());
                  animeData.setAlternateTitles(altTitles);
               } else {
                  animeData.getAlternateTitles().add(info.getValue());
               }
               break;
            case "Genres":
               if (animeData.getGenres() == null) {
                  final List<String> genres = new ArrayList<>();
                  genres.add(info.getValue());
                  animeData.setGenres(genres);
               } else {
                  animeData.getGenres().add(info.getValue());
               }
               break;
            case "Themes":
               if (animeData.getThemes() == null) {
                  final List<String> themes = new ArrayList<>();
                  themes.add(info.getValue());
                  animeData.setThemes(themes);
               } else {
                  animeData.getThemes().add(info.getValue());
               }
               break;
            case "Objectionable content":
               animeData.setViewerRating(info.getValue());
               break;
            case "Plot Summary":
               animeData.setPlotSummary(info.getValue());
               break;
            case "Running time":
               animeData.setRunningTime(info.getValue());
               break;
            case "Number of episodes":
               animeData.setNumberOfEpisodes(Integer.parseInt(info.getValue()));
               break;
            case "Vintage":
               animeData.setVintage(info.getValue());
               break;
            case "Official website":
               animeData.getWebsites().add(new Website(info.getHref(), info.getValue()));
               break;
         }
      }

      for (Credit credit : anime.getCredit()) {
         switch (credit.getTask()) {
            case "Animation Production":
               animeData.setProduction(credit.getCompany());
               break;
         }
      }

      return animeData;
   }

   private static Website createAnimeNewsNetworkLink(int id) {
      return new Website("http://www.animenewsnetwork.com/encyclopedia/anime.php?id="+ id,
                         "AnimeNewsNetwork Encyclopedia");
   }

   public static void cacheAnimeData(final SharedPreferences sharedPreferences, final List<AnimeData> animeData) {
      final Gson gson = new Gson();
      final String json = gson.toJson(animeData);

      final SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(ANIME_DATA_KEY, json);
      editor.apply();
   }

   public static void populateLocalCache(final List<AnimeData> animeData) {
      Log.d("AnimeInfo", "Populating local cache.");
      if (animeDataMap == null) {
         animeDataMap = new SparseArray<>();
      } else {
         animeDataMap.clear();
      }

      for (AnimeData data : animeData) {
         animeDataMap.put(data.getId(), data);
      }
   }

   public static List<AnimeData> getAnimeData(final SharedPreferences sharedPreferences) {
      final Gson gson = new Gson();
      final Type listType = new TypeToken<List<AnimeData >>(){}.getType();

      final String json = sharedPreferences.getString(ANIME_DATA_KEY, null);
      if (json != null) {
         return gson.fromJson(json, listType);
      }

      return new ArrayList<>();
   }
}
