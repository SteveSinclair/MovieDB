package com.example.android.mymoviedb.network;

import com.example.android.mymoviedb.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://api.themoviedb.org/3/movie/";

    @GET("popular")
    Call<MoviesResponse> getPopularMovies(
            @Query("api_key") String apiKey
    );

    @GET("top_rated")
    Call<MoviesResponse> getTopRatedMovies(
            @Query("api_key") String apiKey
    );
}
