package com.piecakepie.animeinfo.service;


import com.piecakepie.animeinfo.dto.Ann;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnimeNewsNetworkService {

   @GET("api.xml")
   Call<Ann> getAnimeList(@Query("anime") String animeId);
}
