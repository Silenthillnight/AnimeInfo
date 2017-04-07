package com.piecakepie.animeinfo.dagger.module;

import android.content.Context;

import com.piecakepie.animeinfo.manager.AnimeNewsNetworkManager;
import com.piecakepie.animeinfo.persistence.DataStore;
import com.piecakepie.animeinfo.service.AnimeNewsNetworkService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for app wide singletons
 */
@Module
public class AppModule {

   private Context appContext;

   public AppModule(Context appContext) {
      this.appContext = appContext;
   }

   @Provides
   @Singleton
   Context provideContext() {
      return appContext;
   }

   @Provides
   @Singleton
   DataStore provideDataStore(Context context) {
      return new DataStore(context);
   }

   @Provides
   @Singleton
   AnimeNewsNetworkManager provideAnimeNewsNetworkManager(AnimeNewsNetworkService service) {
      return new AnimeNewsNetworkManager(service);
   }
}
