package com.dflorencia.themovierepository

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapi.tv_show.TvShow
import com.dflorencia.themoviedatabase.movie.MovieType
import com.dflorencia.themoviedatabase.tv_show.TvShowType

interface TheMovieRepository {
    suspend fun refreshData()

    @VisibleForTesting
    suspend fun refreshMovies(type: MovieType)

    @VisibleForTesting
    suspend fun refreshTvShows(type: TvShowType)

    val moviesTopRated: LiveData<List<Movie>>
    val moviesPopular: LiveData<List<Movie>>
    val moviesUpcoming: LiveData<List<Movie>>
    val tvShowsTopRated: LiveData<List<TvShow>>
    val tvShowsPopular: LiveData<List<TvShow>>
    val tvShowsAiringToday: LiveData<List<TvShow>>
}