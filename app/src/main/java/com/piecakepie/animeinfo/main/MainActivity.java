package com.piecakepie.animeinfo.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.piecakepie.animeinfo.R;
import com.piecakepie.animeinfo.base.BaseActivity;
import com.piecakepie.animeinfo.detail.DetailFragment;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.ui.AnimeDataAdapter;
import com.piecakepie.animeinfo.util.Constants;
import com.piecakepie.animeinfo.util.DataUtil;
import com.piecakepie.animeinfo.util.DrawerEnum;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
   extends BaseActivity<MainContract.View, MainContract.Presenter>
   implements MainContract.View, SwipeRefreshLayout.OnRefreshListener {

   @BindView(R.id.recyclerView)
   RecyclerView recyclerView;

   @BindView(R.id.contentView)
   SwipeRefreshLayout contentView;

   @BindView(R.id.loadingView)
   View loadingView;

   @BindView(R.id.errorView)
   View errorView;

   AnimeDataAdapter adapter;

   int arrayId;

   Fragment currentFragment;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      addChildView(R.layout.activity_list);
      ButterKnife.bind(this);

      init();
   }

   @NonNull
   @Override
   public MainContract.Presenter createPresenter() {
      return new MainPresenter();
   }

   @Override
   public void onRefresh() {
      getData(true);
   }

   @Override
   protected void onNewIntent(Intent intent) {
      super.onNewIntent(intent);

      if (currentFragment != null) {
         getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
         currentFragment = null;
      }

      getArrayId(intent);
   }

   public void init() {
      // Setup contentView = SwipeRefreshView
      contentView.setOnRefreshListener(this);

      // Initialize adapter
      adapter = new AnimeDataAdapter(new AnimeDataAdapter.OnItemClickListener() {
         @Override
         public void onItemClick(AnimeDataAdapter.ViewHolder holder, AnimeData item) {
            currentFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.EXTRA_ANIME_ID, item.getId());
            currentFragment.setArguments(bundle);
            currentFragment.setEnterTransition(new Fade());
            currentFragment.setExitTransition(new Fade());

            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.contentMain, currentFragment)
                                       .addToBackStack(null)
                                       .commit();
         }
      });

      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setAdapter(adapter);

      getArrayId(getIntent());
   }

   public void getArrayId(Intent intent) {
      boolean refresh = false;
      SharedPreferences sharedPreferences = getSharedPreferences(DataUtil.SHARED_PREFERENCES_KEY, MODE_PRIVATE);

      arrayId = sharedPreferences.getInt(Constants.CURRENT_ARRAY_ID, 0);

      if (arrayId == 0) {
         arrayId = DrawerEnum.fall_2016.getArrayId();
         refresh = true;
      }

      if (intent != null) {
         final int intentArrayId = intent.getIntExtra(Constants.EXTRA_ARRAY_ID, 0);
         Log.d("AnimeInfo", "Current arrayId: " + arrayId + ", new arrayId: " + intentArrayId);
         if (intentArrayId != 0 && arrayId != intentArrayId) {
            Log.d("AnimeInfo", "New array found!  Refreshing data!");
            arrayId = intentArrayId;
            refresh = true;
         }
      }

      sharedPreferences.edit().putInt(Constants.CURRENT_ARRAY_ID, arrayId).apply();
      getData(refresh);
   }

   public void getData(boolean refresh) {
      if (refresh) {
         adapter.clearAnimeData();
         adapter.notifyDataSetChanged();
      }

      setTitle(DrawerEnum.getTitleByArrayid(arrayId));
      loadData(getResources().obtainTypedArray(arrayId), refresh);
   }

   public void setData(List<AnimeData> data) {
      adapter.setAnimeDataList(data);
      adapter.notifyDataSetChanged();
   }

   public void addData(AnimeData data) {
      int position = adapter.addAnimeData(data);
      adapter.notifyItemInserted(position);
      showLoading(false);
   }

   public void loadData(TypedArray animeIdArray, boolean pullToRefresh) {
      Log.d("AnimeInfo", "Load Data in Activity");

      final List<String> animeIdList = new ArrayList<>();

      for (int i = 0; i < animeIdArray.length(); i++) {
         animeIdList.add(animeIdArray.getString(i));
      }

      animeIdArray.recycle();

      showLoading(true);
      presenter.pullData(animeIdList, pullToRefresh);
   }

   public void showContent() {
      Log.d("AnimeInfo", "Show Content in Activity");
      showLoading(false);
      contentView.setRefreshing(false);
   }

   public void setTitle(String title) {
      updateTitle(title);
   }

   public void showError(Throwable e) {
      contentView.setRefreshing(false);
      showLoading(false);
      errorView.setVisibility(View.VISIBLE);
      Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
   }

   public void showLoading(boolean show) {
      Log.d("AnimeInfo", "Show loading screen: " + show);
      loadingView.setVisibility(show ? View.VISIBLE : View.GONE);
   }
}
