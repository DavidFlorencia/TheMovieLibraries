package com.dflorencia.themovierepository

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapi.TmdbApi
import com.dflorencia.themovieapi.tv_show.TvShow
import com.dflorencia.themoviedatabase.movie.MovieDao
import com.dflorencia.themoviedatabase.tv_show.TvShowDao
import com.dflorencia.themoviedatabase.movie.MovieType
import com.dflorencia.themoviedatabase.tv_show.TvShowType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val movieDao: MovieDao,
                                        private val tvShowDao: TvShowDao,
                                        private val tmdbApi: TmdbApi,
                                        private val apiKey: String) : TheMovieRepository {

    override suspend fun refreshData(){
        withContext(Dispatchers.IO){
            refreshMovies(type = MovieType.TOP_RATED)
            refreshMovies(type = MovieType.POPULAR)
            refreshMovies(type = MovieType.UPCOMING)
            refreshTvShows(type = TvShowType.TOP_RATED)
            refreshTvShows(type = TvShowType.POPULAR)
            refreshTvShows(type = TvShowType.AIRING_TODAY)
        }
    }

    @VisibleForTesting
    override suspend fun refreshMovies(type: MovieType) {
        val response = when (type){
            MovieType.TOP_RATED -> tmdbApi.getTopRatedMovies(apiKey)
            MovieType.POPULAR -> tmdbApi.getPopularMovies(apiKey)
            MovieType.UPCOMING -> tmdbApi.getUpcomingMovies(apiKey)
        }

        response.movies?.asDatabaseModel(type)?.let {
            movieDao.clear(type.value)
            movieDao.insertAll(it)
        }
    }

    @VisibleForTesting
    override suspend fun refreshTvShows(type: TvShowType) {
        val response = when (type){
            TvShowType.TOP_RATED -> tmdbApi.getTopRatedTvShows(apiKey)
            TvShowType.POPULAR -> tmdbApi.getPopularTvShows(apiKey)
            TvShowType.AIRING_TODAY -> tmdbApi.getAiringTodayTvShows(apiKey)
        }

        response.tvShows?.asDatabaseModel(type)?.let {
            tvShowDao.clear(type.value)
            tvShowDao.insertAll(it)
        }
    }

    override val moviesTopRated: LiveData<List<Movie>> =
        Transformations.map(movieDao.getMovies(MovieType.TOP_RATED.value)) {
            it.asApiModel()
        }

    override val moviesPopular: LiveData<List<Movie>> =
        Transformations.map(movieDao.getMovies(MovieType.POPULAR.value)) {
            it.asApiModel()
        }

    override val moviesUpcoming: LiveData<List<Movie>> =
        Transformations.map(movieDao.getMovies(MovieType.UPCOMING.value)) {
            it.asApiModel()
        }

    override val tvShowsTopRated: LiveData<List<TvShow>> =
        Transformations.map(tvShowDao.getTvShows(TvShowType.TOP_RATED.value)) {
            it.asApiModel()
        }

    override val tvShowsPopular: LiveData<List<TvShow>> =
        Transformations.map(tvShowDao.getTvShows(TvShowType.POPULAR.value)) {
            it.asApiModel()
        }

    override val tvShowsAiringToday: LiveData<List<TvShow>> =
        Transformations.map(tvShowDao.getTvShows(TvShowType.AIRING_TODAY.value)) {
            it.asApiModel()
        }
}