package com.piecakepie.animeinfo.dagger.component;

import com.piecakepie.animeinfo.AnimeInfoApplication;
import com.piecakepie.animeinfo.dagger.module.AppModule;
import com.piecakepie.animeinfo.dagger.module.ServiceModule;
import com.piecakepie.animeinfo.manager.AnimeNewsNetworkManager;
import com.piecakepie.animeinfo.persistence.DataStore;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ServiceModule.class})
public interface AppComponent {

   void inject(AnimeInfoApplication application);

   DataStore dataStore();
   AnimeNewsNetworkManager animeNewsNetworkManager();

}
