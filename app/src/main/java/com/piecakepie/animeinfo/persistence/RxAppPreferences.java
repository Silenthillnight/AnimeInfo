package com.piecakepie.animeinfo.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.f2prateek.rx.preferences2.Preference.Adapter;
import java.util.Set;

public class RxAppPreferences {
   private SharedPreferences preferences;
   private RxSharedPreferences rxPreferences;

   public RxAppPreferences() {
   }

   protected void createSharedPreferences(Context context, String storeName) {
      if(this.rxPreferences == null) {
         this.preferences = context.getSharedPreferences(storeName, 0);
         this.rxPreferences = RxSharedPreferences.create(this.preferences);
      }
   }

   @CheckResult
   @NonNull
   protected Preference<String> getString(@NonNull String key, @NonNull String defaultValue) {
      return this.rxPreferences.getString(key, defaultValue);
   }

   @CheckResult
   @NonNull
   protected Preference<String> getString(@NonNull String key) {
      return this.rxPreferences.getString(key);
   }

   @CheckResult
   @NonNull
   protected Preference<Set<String>> getStringSet(@NonNull String key, @NonNull Set<String> defaultValue) {
      return this.rxPreferences.getStringSet(key, defaultValue);
   }

   @CheckResult
   @NonNull
   protected Preference<Set<String>> getStringSet(@NonNull String key) {
      return this.rxPreferences.getStringSet(key);
   }

   @CheckResult
   @NonNull
   protected Preference<Integer> getInteger(@NonNull String key, int defaultValue) {
      return this.rxPreferences.getInteger(key, Integer.valueOf(defaultValue));
   }

   @CheckResult
   @NonNull
   protected Preference<Integer> getInteger(@NonNull String key) {
      return this.rxPreferences.getInteger(key);
   }

   @CheckResult
   @NonNull
   protected Preference<Boolean> getBoolean(@NonNull String key, boolean defaultValue) {
      return this.rxPreferences.getBoolean(key, Boolean.valueOf(defaultValue));
   }

   @CheckResult
   @NonNull
   protected Preference<Boolean> getBoolean(@NonNull String key) {
      return this.rxPreferences.getBoolean(key);
   }

   @CheckResult
   @NonNull
   protected Preference<Float> getFloat(@NonNull String key, float defaultValue) {
      return this.rxPreferences.getFloat(key, Float.valueOf(defaultValue));
   }

   @CheckResult
   @NonNull
   protected Preference<Float> getFloat(@NonNull String key) {
      return this.rxPreferences.getFloat(key);
   }

   @CheckResult
   @NonNull
   protected Preference<Long> getLong(@NonNull String key, long defaultValue) {
      return this.rxPreferences.getLong(key, Long.valueOf(defaultValue));
   }

   @CheckResult
   @NonNull
   protected Preference<Long> getLong(@NonNull String key) {
      return this.rxPreferences.getLong(key);
   }

   @CheckResult
   @NonNull
   public <T> Preference<T> getObject(@NonNull String key, @NonNull T defaultValue, @NonNull Adapter<T> converter) {
      return this.rxPreferences.getObject(key, defaultValue, converter);
   }

   @CheckResult
   @NonNull
   public <T> Preference<T> getObject(@NonNull String key, @NonNull Adapter<T> converter) {
      return this.rxPreferences.getObject(key, converter);
   }

   public void clearAll() {
      this.preferences.edit().clear().apply();
   }
}
