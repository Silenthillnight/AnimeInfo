package com.piecakepie.animeinfo.util;


import com.piecakepie.animeinfo.dto.network.Website;
import com.piecakepie.animeinfo.dto.realm.RealmAnimeData;
import com.piecakepie.animeinfo.dto.realm.RealmWebsite;
import com.piecakepie.animeinfo.dto.realm.common.RealmString;
import com.piecakepie.animeinfo.model.AnimeData;
import io.realm.RealmList;

import java.util.ArrayList;
import java.util.List;

public class RealmUtil {

   public static RealmAnimeData convertToRealmAnimeData(AnimeData animeData) {
      RealmAnimeData realmAnimeData = new RealmAnimeData();
      realmAnimeData.setId(animeData.getId());
      realmAnimeData.setTitle(animeData.getTitle());
      realmAnimeData.setProduction(animeData.getProduction());
      realmAnimeData.setType(animeData.getType());
      realmAnimeData.setViewerRating(animeData.getViewerRating());
      realmAnimeData.setPlotSummary(animeData.getPlotSummary());
      realmAnimeData.setRunningTime(animeData.getRunningTime());
      realmAnimeData.setNumberOfEpisodes(animeData.getNumberOfEpisodes());
      realmAnimeData.setVintage(animeData.getVintage());
      realmAnimeData.setThumbnailUrl(animeData.getThumbnailUrl());
      realmAnimeData.setImageUrl(animeData.getImageUrl());
      realmAnimeData.setAlternateTitles(toRealmStringList(animeData.getAlternateTitles()));
      realmAnimeData.setGenres(toRealmStringList(animeData.getGenres()));
      realmAnimeData.setThemes(toRealmStringList(animeData.getThemes()));
      realmAnimeData.setWebsites(toRealmWebsiteList(animeData.getWebsites()));
      realmAnimeData.setSeasons(toRealmStringList(animeData.getSeasons()));

      return realmAnimeData;
   }

   private static RealmList<RealmString> toRealmStringList(List<String> stringList) {
      RealmList<RealmString> realmList = new RealmList<>();

      if (stringList != null) {
         for (String value : stringList) {
            RealmString realmString = new RealmString();
            realmString.setValue(value);
            realmList.add(realmString);
         }
      }

      return realmList;
   }

   private static RealmList<RealmWebsite> toRealmWebsiteList(List<Website> websiteList) {
      RealmList<RealmWebsite> realmList = new RealmList<>();

      if (websiteList != null) {
         for (Website website : websiteList) {
            RealmWebsite realmWebsite = new RealmWebsite();
            realmWebsite.setUrl(website.getUrl());
            realmWebsite.setLink(website.getLink());
            realmList.add(realmWebsite);
         }
      }

      return realmList;
   }

   public static AnimeData toAnimeData(RealmAnimeData realmAnimeData) {
      AnimeData animeData = new AnimeData();
      animeData.setId(realmAnimeData.getId());
      animeData.setTitle(realmAnimeData.getTitle());
      animeData.setProduction(realmAnimeData.getProduction());
      animeData.setType(realmAnimeData.getType());
      animeData.setViewerRating(realmAnimeData.getViewerRating());
      animeData.setPlotSummary(realmAnimeData.getPlotSummary());
      animeData.setRunningTime(realmAnimeData.getRunningTime());
      animeData.setNumberOfEpisodes(realmAnimeData.getNumberOfEpisodes());
      animeData.setVintage(realmAnimeData.getVintage());
      animeData.setThumbnailUrl(realmAnimeData.getThumbnailUrl());
      animeData.setImageUrl(realmAnimeData.getImageUrl());
      animeData.setAlternateTitles(toStringList(realmAnimeData.getAlternateTitles()));
      animeData.setGenres(toStringList(realmAnimeData.getGenres()));
      animeData.setThemes(toStringList(realmAnimeData.getThemes()));
      animeData.setWebsites(toWebsiteList(realmAnimeData.getWebsites()));
      animeData.setSeasons(toStringList(realmAnimeData.getSeasons()));

      return animeData;
   }

   private static List<String> toStringList(RealmList<RealmString> realmList) {
      List<String> stringList = new ArrayList<>();

      if (realmList != null) {
         for (RealmString realmString : realmList) {
            stringList.add(realmString.getValue());
         }
      }

      return stringList;
   }

   private static List<Website> toWebsiteList(RealmList<RealmWebsite> realmList) {
      List<Website> websiteList = new ArrayList<>();

      if (realmList != null) {
         for (RealmWebsite realmWebsite : realmList) {
            websiteList.add(new Website(realmWebsite.getUrl(), realmWebsite.getLink()));
         }
      }

      return websiteList;
   }
}
