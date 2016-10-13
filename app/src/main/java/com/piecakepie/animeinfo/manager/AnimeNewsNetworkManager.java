package com.piecakepie.animeinfo.manager;


import okhttp3.OkHttpClient;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class AnimeNewsNetworkManager {

   private final static String BASE_URL = "http://cdn.animenewsnetwork.com/encyclopedia/";

   private static Strategy strategy = new AnnotationStrategy();
   private static Serializer serializer = new Persister(strategy);
   private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

   private static Retrofit.Builder builder =
      new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(SimpleXmlConverterFactory.create(serializer));

   public static <T> T createService(Class<T> serviceClass) {
      Retrofit retrofit = builder.client(httpClient.build()).build();

      return retrofit.create(serviceClass);
   }
}
