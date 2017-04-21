package com.piecakepie.animeinfo.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.piecakepie.animeinfo.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimeView extends LinearLayout {

   @BindView(R.id.anime_image)
   ImageView image;

   @BindView(R.id.anime_title)
   TextView title;

   @BindView(R.id.anime_subtitle)
   TextView subtitle;

   @BindView(R.id.anime_detail)
   TextView detail;

   @BindView(R.id.anime_dropdown_text)
   TextView dropdownText;

   @BindView(R.id.anime_dropdown_image)
   ImageView dropdownImage;

   @BindView(R.id.anime_switch)
   Switch selected;

   @BindView(R.id.anime_expanded_detail)
   View expandedDetail;

   private int rotationAngle = 0;

   public AnimeView(Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
      init(context);
   }

   private void init(Context context) {
      setOrientation(HORIZONTAL);
      LayoutInflater.from(context).inflate(R.layout.view_anime, this, true);
      ButterKnife.bind(this);
   }

   public void setImage(String imageUrl) {
      if (imageUrl == null) {
         image.setImageResource(R.drawable.no_image);
      } else {
         Picasso.with(getContext()).load(imageUrl).into(image);
      }
   }

   public void setTitle(String title) {
      this.title.setText(title);
   }

   public void setSubtitle(String subtitle) {
      this.subtitle.setText(subtitle);
   }

   public void setDetail(String detail) {
      this.detail.setText(detail);
   }

   public View getExpandedDetail() {
      return expandedDetail;
   }

   public void setSelected(boolean selected) {
      this.selected.setChecked(selected);
   }

   public void setOnClickListener(OnClickListener listener) {
      dropdownText.setOnClickListener(listener);
      dropdownImage.setOnClickListener(listener);
   }

   public void showDetail(boolean showDetail) {
      if (showDetail) {
         rotateDropdownImage(VISIBLE);
      } else {
         rotateDropdownImage(GONE);
      }
   }

   public void rotateDropdownImage(int visibility) {
      expandedDetail.setVisibility(visibility);

//      int rotation = 180;
//      if (visibility == GONE) {
//         rotation = -180;
//      }
//      ObjectAnimator anim = ObjectAnimator.ofFloat(dropdownImage,
//                                                   "rotation",
//                                                   rotationAngle,
//                                                   rotationAngle + rotation);
//      anim.setDuration(200);
//      anim.start();
//      rotationAngle += rotation;
//      rotationAngle = rotationAngle%360;
   }
}
