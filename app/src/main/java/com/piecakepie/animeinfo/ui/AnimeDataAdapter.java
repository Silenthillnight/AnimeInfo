package com.piecakepie.animeinfo.ui;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.piecakepie.animeinfo.R;
import com.piecakepie.animeinfo.model.AnimeData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AnimeDataAdapter extends RecyclerView.Adapter<AnimeDataAdapter.ViewHolder> {

   public interface OnItemClickListener {
      void onItemClick(ViewHolder holder, AnimeData item);
   }

   private List<AnimeData> animeDataList;
   private final OnItemClickListener listener;

   public AnimeDataAdapter(OnItemClickListener listener) {
      this.listener = listener;
      animeDataList = new ArrayList<>();
   }

   public void setAnimeDataList(List<AnimeData> animeDataList) {
      this.animeDataList = animeDataList;
   }

   public int addAnimeData(AnimeData animeData) {
      this.animeDataList.add(animeData);
      return animeDataList.size() - 1;
   }

   public void clearAnimeData() {
      this.animeDataList.clear();
   }

   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View animeDataView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_item, parent, false);
      return new ViewHolder(animeDataView);
   }

   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {
      holder.bind(animeDataList.get(position), listener);
   }

   @Override
   public int getItemCount() {
      return animeDataList == null ? 0 : animeDataList.size();
   }

   public static class ViewHolder extends RecyclerView.ViewHolder {

      final Drawable defaultImage;

      @BindView(R.id.row_image)
      ImageView thumbnail;

      @BindView(R.id.row_text)
      TextView title;

      ViewHolder(View itemView) {
         super(itemView);
         ButterKnife.bind(this, itemView);

         defaultImage = itemView.getResources().getDrawable(R.drawable.no_image, null);
      }

      public ImageView getThumbnail() {
         return thumbnail;
      }

      public TextView getTitle() {
         return title;
      }

      void bind(final AnimeData data, final OnItemClickListener listener) {
         title.setText(data.getTitle());
         if (data.getThumbnailUrl() == null) {
            thumbnail.setImageDrawable(defaultImage);
         } else {
            Picasso.with(itemView.getContext()).load(data.getImageUrl()).into(thumbnail);
         }
         itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.onItemClick(ViewHolder.this, data);
            }
         });
      }
   }
}
