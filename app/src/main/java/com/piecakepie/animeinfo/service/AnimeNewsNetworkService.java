package com.piecakepie.animeinfo.service;


import com.piecakepie.animeinfo.dto.network.Ann;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnimeNewsNetworkService {

   @GET("api.xml")
   Single<Ann> getAnimeList(@Query("anime") String animeId);
}
