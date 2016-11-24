package com.piecakepie.animeinfo.dto;


public class Website {

   String url;
   String link;

   public Website(String url, String link) {
      this.url = url;
      this.link = link;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getLink() {
      return link;
   }

   public void setLink(String link) {
      this.link = link;
   }

   @Override
   public String toString() {
      return "Website{" +
             "url='" + url + '\'' +
             ", link='" + link + '\'' +
             '}';
   }
}
