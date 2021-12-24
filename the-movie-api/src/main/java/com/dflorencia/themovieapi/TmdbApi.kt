package com.dflorencia.themovieapi

import com.dflorencia.themovieapi.movie.MoviePage
import com.dflorencia.themovieapi.movie.MovieTrailerPage
import com.dflorencia.themovieapi.tv_show.TvShowPage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey:String): MoviePage

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey:String): MoviePage

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey:String): MoviePage

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(@Query("api_key") apiKey:String): TvShowPage

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apiKey:String): TvShowPage

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvShows(@Query("api_key") apiKey:String): TvShowPage

    // Borrar estos
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