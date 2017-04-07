package com.piecakepie.animeinfo.dagger.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.piecakepie.animeinfo.service.AnimeNewsNetworkService;
import com.piecakepie.animeinfo.service.impl.AnimeNewsNetworkServiceImpl;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Module
public class ServiceModule {

   private final static String BASE_URL = "http://cdn.animenewsnetwork.com/encyclopedia/";

   private static Strategy strategy = new AnnotationStrategy();
   private static Serializer serializer = new Persister(strategy);

   @Provides
   @Singleton
   Retrofit retrofit() {
      final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

      return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
            .build();
   }

   @Provides
   @Singleton
   AnimeNewsNetworkService provideAnimeNewsNetworkService(Retrofit retrofit) {
      return new AnimeNewsNetworkServiceImpl(retrofit.create(AnimeNewsNetworkService.class));
   }
}
