package com.piecakepie.animeinfo.dto.realm;


import com.piecakepie.animeinfo.dto.realm.common.RealmString;
import io.realm.RealmList;
import io.realm.RealmObject;

public class RealmAnimeData extends RealmObject {

   private int id;
   private String title;
   private String production;
   private String type;
   private RealmList<RealmString> alternateTitles;
   private RealmList<RealmString> genres;
   private RealmList<RealmString> themes;
   private String viewerRating;
   private String plotSummary;
   private String runningTime;
   private Integer numberOfEpisodes;
   private String vintage;
   private RealmList<RealmWebsite> websites;
   private String thumbnailUrl;
   private String imageUrl;
   private RealmList<RealmString> seasons;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getProduction() {
      return production;
   }

   public void setProduction(String production) {
      this.production = production;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public RealmList<RealmString> getAlternateTitles() {
      return alternateTitles;
   }

   public void setAlternateTitles(RealmList<RealmString> alternateTitles) {
      this.alternateTitles = alternateTitles;
   }

   public RealmList<RealmString> getGenres() {
      return genres;
   }

   public void setGenres(RealmList<RealmString> genres) {
      this.genres = genres;
   }

   public RealmList<RealmString> getThemes() {
      return themes;
   }

   public void setThemes(RealmList<RealmString> themes) {
      this.themes = themes;
   }

   public String getViewerRating() {
      return viewerRating;
   }

   public void setViewerRating(String viewerRating) {
      this.viewerRating = viewerRating;
   }

   public String getPlotSummary() {
      return plotSummary;
   }

   public void setPlotSummary(String plotSummary) {
      this.plotSummary = plotSummary;
   }

   public String getRunningTime() {
      return runningTime;
   }

   public void setRunningTime(String runningTime) {
      this.runningTime = runningTime;
   }

   public Integer getNumberOfEpisodes() {
      return numberOfEpisodes;
   }

   public void setNumberOfEpisodes(Integer numberOfEpisodes) {
      this.numberOfEpisodes = numberOfEpisodes;
   }

   public String getVintage() {
      return vintage;
   }

   public void setVintage(String vintage) {
      this.vintage = vintage;
   }

   public RealmList<RealmWebsite> getWebsites() {
      return websites;
   }

   public void setWebsites(RealmList<RealmWebsite> websites) {
      this.websites = websites;
   }

   public String getThumbnailUrl() {
      return thumbnailUrl;
   }

   public void setThumbnailUrl(String thumbnailUrl) {
      this.thumbnailUrl = thumbnailUrl;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   public RealmList<RealmString> getSeasons() {
      return seasons;
   }

   public void setSeasons(RealmList<RealmString> seasons) {
      this.seasons = seasons;
   }
}
