package com.piecakepie.animeinfo.manager;

import android.text.TextUtils;

import com.piecakepie.animeinfo.dto.network.Anime;
import com.piecakepie.animeinfo.dto.network.Ann;
import com.piecakepie.animeinfo.model.AnimeData;
import com.piecakepie.animeinfo.service.AnimeNewsNetworkService;
import com.piecakepie.animeinfo.util.DataUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AnimeNewsNetworkManager {

   private AnimeNewsNetworkService animeNewsNetworkService;

   public AnimeNewsNetworkManager(AnimeNewsNetworkService animeNewsNetworkService) {
      this.animeNewsNetworkService = animeNewsNetworkService;
   }

   public Observable<AnimeData> getAnimeData(final String seasonName, final List<String> animeIdList) {
      return animeNewsNetworkService
            .getAnimeList(createQueryString(animeIdList))
            .observeOn(Schedulers.io())
            .flatMapObservable(new Function<Ann, ObservableSource<Anime>>() {
               @Override
               public ObservableSource<Anime> apply(Ann ann) throws Exception {
                  return Observable.fromIterable(ann.getAnime());
               }
            })
            .observeOn(Schedulers.computation())
            .map(anime -> DataUtil.convertToAnimeData(anime, seasonName));
   }

   /**
    * Concatenates anime ids with a '/' in between each id because ANN has a terrible query system
    * @return  concatenated query string
    */
   private String createQueryString(List<String> animeIdList) {
      return TextUtils.join("/", animeIdList);
   }
}
