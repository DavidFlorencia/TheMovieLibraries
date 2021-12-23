package com.dflorencia.themovieapp.api

import com.dflorencia.themovieapp.api.MoviePage
import com.dflorencia.themovieapp.api.MovieTrailerPage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey:String): MoviePage

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey:String): MoviePage

    @GET("search/movie")
    suspend fun searchMoviesFromNetwork(
        @Query("api_key") apiKey:String,
        @Query("query") query: String): MoviePage

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey:String
    ): MovieTrailerPage
}