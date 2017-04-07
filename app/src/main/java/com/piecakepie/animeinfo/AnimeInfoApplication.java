package com.piecakepie.animeinfo;

import android.app.Application;

import com.piecakepie.animeinfo.dagger.component.AppComponent;
import com.piecakepie.animeinfo.dagger.component.DaggerAppComponent;
import com.piecakepie.animeinfo.dagger.module.AppModule;


public class AnimeInfoApplication extends Application {

   private AppComponent appComponent;

   @Override
   public void onCreate() {
      super.onCreate();

      appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
      //appComponent.inject(this);
   }

   public AppComponent getAppComponent() {
      return appComponent;
   }
}
