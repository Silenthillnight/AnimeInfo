package com.piecakepie.animeinfo.main;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.piecakepie.animeinfo.R;
import com.piecakepie.animeinfo.model.AnimeData;
import com.squareup.picasso.Picasso;

import java.util.List;

class AnimeDataAdapter extends RecyclerView.Adapter<AnimeDataAdapter.ViewHolder> {

   private List<AnimeData> animeDataList;
   private Context context;

   AnimeDataAdapter(Context context) {
      this.context = context;
   }

   void setAnimeDataList(List<AnimeData> animeDataList) {
      this.animeDataList = animeDataList;
   }

   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      Context context = parent.getContext();
      LayoutInflater inflater = LayoutInflater.from(context);

      View animeDataView = inflater.inflate(R.layout.row_view, parent, false);

      return new ViewHolder(animeDataView);
   }

   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {
      AnimeData animeData = animeDataList.get(position);

      ImageView imageView = holder.imageView;
      Picasso.with(context).load(animeData.getThumbnailUrl()).into(imageView);
      TextView textView = holder.textView;
      textView.setText(animeData.getTitle());

   }

   @Override
   public int getItemCount() {
      return animeDataList == null ? 0 : animeDataList.size();
   }

   static class ViewHolder extends RecyclerView.ViewHolder {

      ImageView imageView;
      TextView textView;

      ViewHolder(View itemView) {
         super(itemView);

         imageView = (ImageView) itemView.findViewById(R.id.row_image);
         textView = (TextView) itemView.findViewById(R.id.row_text);
      }
   }
}
