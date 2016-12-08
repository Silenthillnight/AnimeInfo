package com.piecakepie.animeinfo.dto.network;

public class Img {

   private String src;
   private int width;
   private int height;

   public String getSrc() {
      return src;
   }

   public void setSrc(String src) {
      this.src = src;
   }

   public int getWidth() {
      return width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getHeight() {
      return height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   @Override
   public String toString() {
      return "Img{" +
             "src='" + src + '\'' +
             ", width=" + width +
             ", height=" + height +
             '}';
   }
}
