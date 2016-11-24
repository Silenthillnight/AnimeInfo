package com.piecakepie.animeinfo.base;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.piecakepie.animeinfo.R;
import com.piecakepie.animeinfo.ui.NavigationItemListener;

public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> {

   protected DrawerLayout drawerLayout;
   protected Toolbar toolbar;
   protected NavigationView navigationView;
   protected FrameLayout contentView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      mvpDelegate.onCreate(savedInstanceState);
      setContentView(R.layout.activity_base);

      initViews();

      setSupportActionBar(toolbar);

      ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
      drawerLayout.addDrawerListener(toggle);
      toggle.syncState();

      navigationView.setNavigationItemSelectedListener(new NavigationItemListener(this, drawerLayout));
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      mvpDelegate.onDestroy();
   }

   @Override
   protected void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
      mvpDelegate.onSaveInstanceState(outState);
   }

   protected void initViews() {
      drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
      toolbar = (Toolbar) findViewById(R.id.toolbar);
      navigationView = (NavigationView) findViewById(R.id.navView);
      contentView = (FrameLayout) findViewById(R.id.contentMain);
   }

   @Override
   public void onBackPressed() {
      if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
         drawerLayout.closeDrawer(GravityCompat.START);
      } else {
         super.onBackPressed();
      }
   }

   public void addChildView(int layoutId) {
      contentView.removeAllViews();
      View view = getLayoutInflater().inflate(layoutId, contentView, false);
      contentView.addView(view);
   }
}
