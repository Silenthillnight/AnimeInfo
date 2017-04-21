package com.piecakepie.animeinfo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.piecakepie.animeinfo.R;

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
   private Drawable divider;

   public SimpleDividerItemDecoration(Context context) {
      divider = ContextCompat.getDrawable(context, R.drawable.divider);
   }

   @Override
   public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
      final int left = parent.getPaddingLeft();
      final int right = parent.getWidth() - parent.getPaddingRight();
      final int childCount = parent.getChildCount();
      final int dividerHeight = divider.getIntrinsicHeight();

      for (int i = 1; i < childCount; i++) {
         final View child = parent.getChildAt(i);
         final RecyclerView.LayoutParams params =
               (RecyclerView.LayoutParams) child.getLayoutParams();
         final int ty = (int) (child.getTranslationY() + 0.5f);
         final int top = child.getTop() - params.topMargin + ty;
         final int bottom = top + dividerHeight;
         divider.setBounds(left, top, right, bottom);
         divider.draw(c);
      }
   }
}