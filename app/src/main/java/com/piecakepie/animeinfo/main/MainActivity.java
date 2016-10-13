package com.piecakepie.animeinfo.main;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.piecakepie.animeinfo.R;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
   extends MvpLceActivity<SwipeRefreshLayout, List<AnimeData>, MainView, MainPresenter>
   implements MainView, SwipeRefreshLayout.OnRefreshListener {

   @BindView(R.id.recyclerView)
   RecyclerView recyclerView;

   AnimeDataAdapter adapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      ButterKnife.bind(this);

      // Setup contentView = SwipeRefreshView
      contentView.setOnRefreshListener(this);

      // Initialize adapter
      adapter = new AnimeDataAdapter(this);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setAdapter(adapter);
      loadData(false);
   }

   @NonNull
   @Override
   public MainPresenter createPresenter() {
      TypedArray animeIdArray = getResources().obtainTypedArray(R.array.winter_2016);
      final List<String> animeIdList = new ArrayList<>();

      for (int i = 0; i < animeIdArray.length(); i++) {
         animeIdList.add(animeIdArray.getString(i));
      }

      animeIdArray.recycle();
      return new MainPresenter(getSharedPreferences(DataUtil.SHARED_PREFERENCES_KEY, MODE_PRIVATE), animeIdList);
   }

   @Override
   public void onRefresh() {
      loadData(true);
   }

   @Override
   protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
      // TODO: Set up more robust error messages
      return null;
   }

   @Override
   public void setData(List<AnimeData> data) {
      adapter.setAnimeDataList(data);
      adapter.notifyDataSetChanged();
   }

   @Override
   public void loadData(boolean pullToRefresh) {
      presenter.pullData(pullToRefresh);
   }

   @Override
   public void showContent() {
      super.showContent();
      contentView.setRefreshing(false);
   }

   @Override
   public void showError(Throwable e, boolean pullToRefresh) {
      super.showError(e, pullToRefresh);
      contentView.setRefreshing(false);
   }
}
