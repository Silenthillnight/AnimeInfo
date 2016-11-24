package com.piecakepie.animeinfo.util;


import android.support.annotation.ArrayRes;
import com.piecakepie.animeinfo.R;

public enum DrawerEnum {
   fall_2016("Fall 2016", R.array.fall_2016),
   winter_2016("Winter 2016", R.array.winter_2016);

   @ArrayRes
   int arrayId;

   String title;

   DrawerEnum(String title, int arrayId) {
      this.title = title;
      this.arrayId = arrayId;
   }

   @ArrayRes
   public int getArrayId() {
      return arrayId;
   }

   public String getTitle() {
      return title;
   }

   public static String getTitleByArrayid(int arrayId) {
      for (DrawerEnum drawerEnum : DrawerEnum.values()) {
         if (drawerEnum.getArrayId() == arrayId) {
            return drawerEnum.getTitle();
         }
      }

      return null;
   }
}
