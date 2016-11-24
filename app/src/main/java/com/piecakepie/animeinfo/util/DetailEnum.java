package com.piecakepie.animeinfo.util;


public enum DetailEnum {
   ALTERNATE_TITLES("Alternate Titles"),
   TYPE("Type"),
   GENRES("Genres"),
   THEMES("Themes"),
   VIEWER_RATING("Viewer Rating"),
   RUNNING_TIME("Running Time"),
   NUMBER_OF_EPISODES("Number of Episodes"),
   VINTAGE("Vintage"),
   WEBSITES("Websites");

   private String header;

   DetailEnum(String header) {
      this.header = header;
   }

   public String getHeader() {
      return header;
   }
}
