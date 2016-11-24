package com.piecakepie.animeinfo.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import com.piecakepie.animeinfo.R;
import com.piecakepie.animeinfo.base.BaseActivity;
import com.piecakepie.animeinfo.main.MainActivity;
import com.piecakepie.animeinfo.util.Constants;
import com.piecakepie.animeinfo.util.DrawerEnum;


public class NavigationItemListener implements NavigationView.OnNavigationItemSelectedListener {

   private BaseActivity activity;
   private DrawerLayout drawerLayout;

   public NavigationItemListener(BaseActivity activity, DrawerLayout drawerLayout) {
      this.activity = activity;
      this.drawerLayout = drawerLayout;
   }

   @Override
   public boolean onNavigationItemSelected(@NonNull MenuItem item) {

      int id = item.getItemId();

      switch (id) {
         case R.id.fall_2016:
            navigateToListActivity(DrawerEnum.fall_2016);
            break;
         case R.id.winter_2016:
            navigateToListActivity(DrawerEnum.winter_2016);
            break;
      }

      drawerLayout.closeDrawer(GravityCompat.START);

      return true;
   }

   private void navigateToListActivity(DrawerEnum drawerEnum) {
      Intent listIntent = new Intent(activity, MainActivity.class);
      listIntent.putExtra(Constants.EXTRA_ARRAY_ID, drawerEnum.getArrayId());
      activity.startActivity(listIntent);
   }
}
