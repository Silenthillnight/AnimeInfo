package com.piecakepie.animeinfo.dagger.module;

import com.piecakepie.animeinfo.dagger.ActivityScope;
import com.piecakepie.animeinfo.main.MainContract;
import com.piecakepie.animeinfo.main.MainPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ActivityModule {

   @Binds
   @ActivityScope
   abstract MainContract.Presenter provideMainPresenter(MainPresenter presenter);
}
