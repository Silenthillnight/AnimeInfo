package com.piecakepie.animeinfo.base;


import android.content.Context;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.piecakepie.animeinfo.AnimeInfoApplication;

public class BasePresenter<V extends MvpView> extends MvpNullObjectBasePresenter<V> {

   private Context context;

   public BasePresenter() {
      this.context = AnimeInfoApplication.getAppContext();
   }

   public Context getContext() {
      return context;
   }

   public void setContext(Context context) {
      this.context = context;
   }
}
