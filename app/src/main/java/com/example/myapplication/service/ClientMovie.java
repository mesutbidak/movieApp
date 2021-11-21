package com.example.myapplication.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ClientMovie {
    private static final String BASE_URL = "https://api.themoviedb.org";
    private static Retrofit retrofit;
    public static int PAGE = 1;
    public static String CATEGORY = "movie";
    public static String API_KEY = "42e95e388d8ce1529f20bacf726b5d62";
    public static String LANGUAGE="en-US";
    public static String SEARCH_QUERY="you";

    /**
     * Default creating Retrofit object
     * Added and interceptor for seeing the request & respond data on log.
     */
    public static Retrofit getApiClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
