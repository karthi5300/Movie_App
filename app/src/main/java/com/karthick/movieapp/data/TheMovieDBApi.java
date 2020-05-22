package com.karthick.movieapp.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDBApi {

    @GET("3/movie/now_playing?")
    Call<CurrentMoviesData> getCurrentMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("user") String user);

    @GET("3/movie/{movie_id}?")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") String movieId, @Query("api_key") String apiKey);

}
