package com.piecakepie.animeinfo.persistence;

import android.content.Context;

import javax.inject.Inject;

/**
 * Helper class to set and retrieve the app preferences.
 */
public class DataStore extends RxAppPreferences {

   private static final String STORE_NAME = "anime_info_app_preferences";

   @Inject
   public DataStore(Context context) {
      createSharedPreferences(context, STORE_NAME);
   }
}