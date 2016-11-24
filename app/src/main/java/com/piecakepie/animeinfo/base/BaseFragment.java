package com.piecakepie.animeinfo.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public abstract class BaseFragment<V extends MvpView, P extends MvpPresenter<V>>
   extends MvpFragment<V, P>
   implements BaseView {

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater,
                            @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {

      View view = inflater.inflate(getRootLayoutId(), container, false);
      ButterKnife.bind(this, view);
      return view;
   }

   protected abstract int getRootLayoutId();
}
