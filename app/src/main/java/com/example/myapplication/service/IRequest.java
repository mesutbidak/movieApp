package com.example.myapplication.service;

import com.example.myapplication.data.detail.MovieDetailModel;
import com.example.myapplication.data.main_list.results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRequest {
    /*@GET("/3/movie/{id}")
    Call<MovieDetailModel> getMovieDetail(
            @Path("id") int id,
            @Query("api_key") String apiKey
            );
    @GET("/3/search/movie")
    Call<List<MovieModel>> getMovieList(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("search_query") String searchQuery,
            @Query("page") int page

    );*/
    @GET("/3/movie/{id}?api_key=42e95e388d8ce1529f20bacf726b5d62")
    Call<MovieDetailModel> getMovieDetail(
            @Path("id") int id
    );
    @GET("/3/search/movie?api_key=42e95e388d8ce1529f20bacf726b5d62&language=en-US")
    Call<results> getMovieList(
            @Query("query") String query,
            @Query("page") int page
    );

}
//https://api.themoviedb.org/3/search/movie?api_key=%3capi_key%3e&language=en-US&query=%3csearch_query%3e&page=1
//https://api.themoviedb.org/3/movie/550?api_key=42e95e388d8ce1529f20bacf726b5d62
//api_key= 42e95e388d8ce1529f20bacf726b5d62
//https://api.themoviedb.org/3/search/movie?api_key=42e95e388d8ce1529f20bacf726b5d62&language=en-US&query=Zombie%20Comatose&page=1