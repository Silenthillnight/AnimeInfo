package com.piecakepie.animeinfo.dto.realm;

import io.realm.RealmObject;


public class RealmWebsite extends RealmObject {

   private String url;
   private String link;

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
}
