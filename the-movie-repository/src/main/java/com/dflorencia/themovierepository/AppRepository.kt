package com.dflorencia.themovierepository

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapi.TmdbApi
import com.dflorencia.themovieapi.movie.MoviePage
import com.dflorencia.themovieapi.tv_show.TvShow
import com.dflorencia.themoviedatabase.movie.MovieDao
import com.dflorencia.themoviedatabase.tv_show.TvShowDao
import com.dflorencia.themovierepository.enums.MovieType
import com.dflorencia.themovierepository.enums.TvShowType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.TestOnly
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val movieDao: MovieDao,
                                        private val tvShowDao: TvShowDao,
                                        private val tmdbApi: TmdbApi,
                                        private val apiKey: String){

    suspend fun refreshData(){
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
    suspend fun refreshMovies(type: MovieType) {
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
    suspend fun refreshTvShows(type: TvShowType) {
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

    private val _key = MutableLiveData<String>();
    val key: LiveData<String> get() = _key

    suspend fun refreshMovieTrailerKey(movieId:String) {
        val movieTrailerPage = tmdbApi.getMovieTrailers(movieId,apiKey)
        _key.value = movieTrailerPage.movieTrailers?.get(0)?.key.toString()
    }

    val moviesTopRated: LiveData<List<Movie>> =
        Transformations.map(movieDao.getMovies(MovieType.TOP_RATED.value)) {
            it.asApiModel()
        }

    val moviesPopular: LiveData<List<Movie>> =
        Transformations.map(movieDao.getMovies(MovieType.POPULAR.value)) {
            it.asApiModel()
        }

    val moviesUpcoming: LiveData<List<Movie>> =
        Transformations.map(movieDao.getMovies(MovieType.UPCOMING.value)) {
            it.asApiModel()
        }

    val tvShowsTopRated: LiveData<List<TvShow>> =
        Transformations.map(tvShowDao.getTvShows(TvShowType.TOP_RATED.value)) {
            it.asApiModel()
        }

    val tvShowsPopular: LiveData<List<TvShow>> =
        Transformations.map(tvShowDao.getTvShows(TvShowType.POPULAR.value)) {
            it.asApiModel()
        }

    val tvShowsAiringToday: LiveData<List<TvShow>> =
        Transformations.map(tvShowDao.getTvShows(TvShowType.AIRING_TODAY.value)) {
            it.asApiModel()
        }
}