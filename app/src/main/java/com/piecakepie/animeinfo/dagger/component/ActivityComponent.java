package com.piecakepie.animeinfo.dagger.component;

import com.piecakepie.animeinfo.dagger.ActivityScope;
import com.piecakepie.animeinfo.dagger.module.ActivityModule;
import com.piecakepie.animeinfo.main.MainActivity;
import com.piecakepie.animeinfo.main.MainContract;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = {AppComponent.class})
public interface ActivityComponent {

   void inject(MainActivity activity);

   MainContract.Presenter mainPresenter();
}
