package com.piecakepie.animeinfo.model;

import com.piecakepie.animeinfo.dto.Website;

import java.util.List;

/**
 * Actual data object for populating data on screen
 */
public class AnimeData {

   protected int id;
   protected String title;
   private String production;
   private String type;
   private List<String> alternateTitles;
   private List<String> genres;
   private List<String> themes;
   private String viewerRating;
   private String plotSummary;
   private String runningTime;
   private Integer numberOfEpisodes;
   private String vintage;
   private List<Website> websites;
   private String thumbnailUrl;
   private String imageUrl;

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

   public List<String> getAlternateTitles() {
      return alternateTitles;
   }

   public void setAlternateTitles(List<String> alternateTitles) {
      this.alternateTitles = alternateTitles;
   }

   public List<String> getGenres() {
      return genres;
   }

   public void setGenres(List<String> genres) {
      this.genres = genres;
   }

   public List<String> getThemes() {
      return themes;
   }

   public void setThemes(List<String> themes) {
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

   public List<Website> getWebsites() {
      return websites;
   }

   public void setWebsites(List<Website> websites) {
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

   @Override
   public String toString() {
      return "AnimeData{" +
             "id=" + id +
             ", title='" + title + '\'' +
             ", production='" + production + '\'' +
             ", type='" + type + '\'' +
             ", alternateTitles=" + alternateTitles +
             ", genres=" + genres +
             ", themes=" + themes +
             ", viewerRating='" + viewerRating + '\'' +
             ", plotSummary='" + plotSummary + '\'' +
             ", runningTime='" + runningTime + '\'' +
             ", numberOfEpisodes=" + numberOfEpisodes +
             ", vintage='" + vintage + '\'' +
             ", websites=" + websites +
             ", thumbnailUrl='" + thumbnailUrl + '\'' +
             ", imageUrl='" + imageUrl + '\'' +
             '}';
   }
}
