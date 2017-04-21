package com.piecakepie.animeinfo.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.piecakepie.animeinfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderView extends LinearLayout {

   @BindView(R.id.header_image)
   ImageView image;

   @BindView(R.id.header_title)
   TextView title;

   @BindView(R.id.header_subtitle)
   TextView subtitle;

   public HeaderView(Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
      init(context);
   }

   private void init(Context context) {
      setOrientation(VERTICAL);
      setGravity(Gravity.CENTER_HORIZONTAL);
      LayoutInflater.from(context).inflate(R.layout.view_header, this, true);
      ButterKnife.bind(this);
   }

   public void setImage(Drawable image) {
      this.image.setImageDrawable(image);
   }

   public void setImage(@DrawableRes int image) {
      this.image.setImageResource(image);
   }

   public void setTitle(String title) {
      this.title.setText(title);
   }

   public void setSubtitle(String subtitle) {
      this.subtitle.setText(subtitle);
   }
}
