package com.piecakepie.animeinfo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.piecakepie.animeinfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SummaryView extends RelativeLayout {

   @BindView(R.id.summary_title)
   TextView title;

   @BindView(R.id.summary_subtitle)
   TextView subtitle;

   @BindView(R.id.summary_status)
   TextView status;

   @BindView(R.id.summary_switch)
   Switch summarySwitch;

   public SummaryView(Context context, AttributeSet attrs) {
      super(context, attrs);
      init(context);
   }

   private void init(Context context) {
      LayoutInflater.from(context).inflate(R.layout.view_summary, this, true);
      ButterKnife.bind(this);
   }

   public void setTitle(String title) {
      this.title.setText(title);
   }

   public void setSubtitle(String subtitle) {
      this.subtitle.setText(subtitle);
   }

   public void setStatus(String status) {
      this.status.setText(status);
   }

   public void setSwitchOnClickListener(Switch.OnCheckedChangeListener listener) {
      this.summarySwitch.setOnCheckedChangeListener(listener);
   }
}
