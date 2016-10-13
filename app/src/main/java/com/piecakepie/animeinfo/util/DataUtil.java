package com.piecakepie.animeinfo.util;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.piecakepie.animeinfo.model.Anime;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.model.Img;
import com.piecakepie.animeinfo.model.Info;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {

   public final static String SHARED_PREFERENCES_KEY = "anime_info_preferences";
   private final static String ANIME_DATA_KEY = "anime_data";

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

      for (Info info : anime.getInfo()) {
         switch (info.getType()) {
            case "Picture":
               for (Img img : info.getImg()) {
                  // Thumbnails from ANN come at 200px and below
                  if (img.getHeight() != 0 && img.getHeight() <= 200) {
                     animeData.setThumbnailUrl(img.getSrc());
                  // Main images from ANN come at greater than 600px height wise
                  } else if (img.getHeight() != 0 && img.getHeight() > 600) {
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
               if (animeData.getWebsites() == null) {
                  final List<String> websites = new ArrayList<>();
                  websites.add(info.getHref());
                  animeData.setWebsites(websites);
               } else {
                  animeData.getWebsites().add(info.getHref());
               }
               break;
         }
      }

      return animeData;
   }

   public static void cacheAnimeData(final SharedPreferences sharedPreferences, final List<AnimeData> animeData) {
      final Gson gson = new Gson();
      final String json = gson.toJson(animeData);

      final SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(ANIME_DATA_KEY, json);
      editor.apply();
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
