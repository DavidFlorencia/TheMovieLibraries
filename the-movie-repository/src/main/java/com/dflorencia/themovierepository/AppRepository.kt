package com.dflorencia.themovierepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapi.TmdbApi
import com.dflorencia.themovieapi.movie.MoviePage
import com.dflorencia.themoviedatabase.movie.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val movieDao: MovieDao,
                                        private val tmdbApi: TmdbApi,
                                        private val apiKey: String){

    suspend fun refreshData(){
        withContext(Dispatchers.IO){
            val response = tmdbApi.getTopRatedMovies(apiKey)

            response.movies?.asDatabaseModel("top_rated")?.let {
                    movieDao.clear("top_rated")
                    movieDao.insertAll(it)
            }
        }
    }

    private val _key = MutableLiveData<String>();
    val key: LiveData<String> get() = _key

    suspend fun refreshMovieTrailerKey(movieId:String) {
        val movieTrailerPage = tmdbApi.getMovieTrailers(movieId,apiKey)
        _key.value = movieTrailerPage.movieTrailers?.get(0)?.key.toString()
    }

    val movies: LiveData<List<Movie>> = Transformations.map(movieDao.getMovies("top_rated")){
        it.asApiModel()
    }
}