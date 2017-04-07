package com.piecakepie.animeinfo.service.impl;

import com.piecakepie.animeinfo.dto.network.Ann;
import com.piecakepie.animeinfo.service.AnimeNewsNetworkService;

import io.reactivex.Single;
import retrofit2.http.Query;


public class AnimeNewsNetworkServiceImpl implements AnimeNewsNetworkService {

   protected AnimeNewsNetworkService animeNewsNetworkService;

   public AnimeNewsNetworkServiceImpl(AnimeNewsNetworkService animeNewsNetworkService) {
      this.animeNewsNetworkService = animeNewsNetworkService;
   }

   @Override
   public Single<Ann> getAnimeList(@Query("anime") String animeId) {
      return animeNewsNetworkService.getAnimeList(animeId);
   }
}
