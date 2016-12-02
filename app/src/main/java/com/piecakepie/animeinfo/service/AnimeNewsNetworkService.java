package com.piecakepie.animeinfo.service;


import com.piecakepie.animeinfo.dto.Ann;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface AnimeNewsNetworkService {

   @GET("api.xml")
   Observable<Ann> getAnimeList(@Query("anime") String animeId);
}
