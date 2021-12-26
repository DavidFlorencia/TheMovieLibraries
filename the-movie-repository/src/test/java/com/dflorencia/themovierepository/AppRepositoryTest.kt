package com.dflorencia.themovierepository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dflorencia.themovieapi.TmdbApi
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapi.movie.MoviePage
import com.dflorencia.themovieapi.tv_show.TvShow
import com.dflorencia.themovieapi.tv_show.TvShowPage
import com.dflorencia.themoviedatabase.movie.MovieDao
import com.dflorencia.themoviedatabase.movie.MovieType
import com.dflorencia.themoviedatabase.tv_show.TvShowDao
import com.dflorencia.themoviedatabase.tv_show.TvShowType
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class AppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val API_KEY = "fake_key"

    private lateinit var repository: AppRepository

    private lateinit var mockMovieDao: MovieDao
    private lateinit var mockTvShowDao: TvShowDao
    private lateinit var mockTmdbApi: TmdbApi

    private val moviePage = MoviePage()

    private val apiMovies = listOf(
        Movie(id = 111, title = "ApiMovie1"),
        Movie(id = 112, title = "ApiMovie2"),
        Movie(id = 113, title = "ApiMovie2")
    )

    private val topRatedMovies = apiMovies.asDatabaseModel(MovieType.TOP_RATED)
    private val popularMovies = apiMovies.asDatabaseModel(MovieType.POPULAR)
    private val upcomingMovies = apiMovies.asDatabaseModel(MovieType.UPCOMING)

    private val tvShowPage = TvShowPage()

    private val apiTvShows = listOf(
        TvShow(id = 121, name = "ApiTvShow1"),
        TvShow(id = 122, name = "ApiTvShow2"),
        TvShow(id = 123, name = "ApiTvShow3")
    )

    private val topRatedTvShows = apiTvShows.asDatabaseModel(TvShowType.TOP_RATED)
    private val popularTvShows = apiTvShows.asDatabaseModel(TvShowType.POPULAR)
    private val airingTodayTvShows = apiTvShows.asDatabaseModel(TvShowType.AIRING_TODAY)

    @Before
    fun setup(){
        mockMovieDao = Mockito.mock(MovieDao::class.java)
        mockTvShowDao = Mockito.mock(TvShowDao::class.java)
        mockTmdbApi = Mockito.mock(TmdbApi::class.java)

        repository = AppRepository(mockMovieDao,mockTvShowDao,mockTmdbApi,API_KEY)
    }

    @Test
    fun launchRefreshMoviesTypeTopRated_verifyCalledMethods(){
        runBlocking {
            // preparar page que será retornada por llamada a api
            moviePage.movies = apiMovies

            // definir valor de retorno necesarios
            Mockito.`when`(mockTmdbApi.getTopRatedMovies(API_KEY)).thenReturn(moviePage)

            // ejecutar metodo a probar
            repository.refreshMovies(type = MovieType.TOP_RATED)

            // validar numero de llamadas a los metodos dentro del metodo a probar
            verify(mockTmdbApi, times(1)).getTopRatedMovies(API_KEY)
            verify(mockTmdbApi, times(0)).getPopularMovies(API_KEY)
            verify(mockTmdbApi, times(0)).getUpcomingMovies(API_KEY)
            verify(mockMovieDao, times(1))
                .clear(MovieType.TOP_RATED.value)
            verify(mockMovieDao, times(1))
                .insertAll(topRatedMovies)
        }
    }

    @Test
    fun launchRefreshMoviesPopular_verifyCalledMethods(){
        runBlocking {
            // preparar page que será retornada por llamada a api
            moviePage.movies = apiMovies

            // definir valor de retorno necesarios
            Mockito.`when`(mockTmdbApi.getPopularMovies(API_KEY)).thenReturn(moviePage)

            // ejecutar metodo a probar
            repository.refreshMovies(type = MovieType.POPULAR)

            // validar numero de llamadas a los metodos dentro del metodo a probar
            verify(mockTmdbApi, times(0)).getTopRatedMovies(API_KEY)
            verify(mockTmdbApi, times(1)).getPopularMovies(API_KEY)
            verify(mockTmdbApi, times(0)).getUpcomingMovies(API_KEY)
            verify(mockMovieDao, times(1))
                .clear(MovieType.POPULAR.value)
            verify(mockMovieDao, times(1))
                .insertAll(popularMovies)
        }
    }

    @Test
    fun launchRefreshMoviesUpcoming_verifyCalledMethods(){
        runBlocking {
            // preparar page que será retornada por llamada a api
            moviePage.movies = apiMovies

            // definir valor de retorno necesarios
            Mockito.`when`(mockTmdbApi.getUpcomingMovies(API_KEY)).thenReturn(moviePage)

            // ejecutar metodo a probar
            repository.refreshMovies(type = MovieType.UPCOMING)

            // validar numero de llamadas a los metodos dentro del metodo a probar
            verify(mockTmdbApi, times(0)).getTopRatedMovies(API_KEY)
            verify(mockTmdbApi, times(0)).getPopularMovies(API_KEY)
            verify(mockTmdbApi, times(1)).getUpcomingMovies(API_KEY)
            verify(mockMovieDao, times(1))
                .clear(MovieType.UPCOMING.value)
            verify(mockMovieDao, times(1))
                .insertAll(upcomingMovies)
        }
    }

    @Test
    fun launchRefreshTvShowsTypeTopRated_verifyCalledMethods(){
        runBlocking {
            // preparar page que será retornada por llamada a api
            tvShowPage.tvShows = apiTvShows

            // definir valor de retorno necesarios
            Mockito.`when`(mockTmdbApi.getTopRatedTvShows(API_KEY)).thenReturn(tvShowPage)

            // ejecutar metodo a probar
            repository.refreshTvShows(type = TvShowType.TOP_RATED)

            // validar numero de llamadas a los metodos dentro del metodo a probar
            verify(mockTmdbApi, times(1)).getTopRatedTvShows(API_KEY)
            verify(mockTmdbApi, times(0)).getPopularTvShows(API_KEY)
            verify(mockTmdbApi, times(0)).getAiringTodayTvShows(API_KEY)
            verify(mockTvShowDao, times(1))
                .clear(TvShowType.TOP_RATED.value)
            verify(mockTvShowDao, times(1))
                .insertAll(topRatedTvShows)
        }
    }

    @Test
    fun launchRefreshTvShowsTypePopular_verifyCalledMethods(){
        runBlocking {
            // preparar page que será retornada por llamada a api
            tvShowPage.tvShows = apiTvShows

            // definir valor de retorno necesarios
            Mockito.`when`(mockTmdbApi.getPopularTvShows(API_KEY)).thenReturn(tvShowPage)

            // ejecutar metodo a probar
            repository.refreshTvShows(type = TvShowType.POPULAR)

            // validar numero de llamadas a los metodos dentro del metodo a probar
            verify(mockTmdbApi, times(0)).getTopRatedTvShows(API_KEY)
            verify(mockTmdbApi, times(1)).getPopularTvShows(API_KEY)
            verify(mockTmdbApi, times(0)).getAiringTodayTvShows(API_KEY)
            verify(mockTvShowDao, times(1))
                .clear(TvShowType.POPULAR.value)
            verify(mockTvShowDao, times(1))
                .insertAll(popularTvShows)
        }
    }

    @Test
    fun launchRefreshAiringTodayTypePopular_verifyCalledMethods(){
        runBlocking {
            // preparar page que será retornada por llamada a api
            tvShowPage.tvShows = apiTvShows

            // definir valor de retorno necesarios
            Mockito.`when`(mockTmdbApi.getAiringTodayTvShows(API_KEY)).thenReturn(tvShowPage)

            // ejecutar metodo a probar
            repository.refreshTvShows(type = TvShowType.AIRING_TODAY)

            // validar numero de llamadas a los metodos dentro del metodo a probar
            verify(mockTmdbApi, times(0)).getTopRatedTvShows(API_KEY)
            verify(mockTmdbApi, times(0)).getPopularTvShows(API_KEY)
            verify(mockTmdbApi, times(1)).getAiringTodayTvShows(API_KEY)
            verify(mockTvShowDao, times(1))
                .clear(TvShowType.AIRING_TODAY.value)
            verify(mockTvShowDao, times(1))
                .insertAll(airingTodayTvShows)
        }
    }

    @Test
    fun launchRefreshData_verifyCalledMethods(){
        runBlocking {
            // preparar pages que serán retornadas por llamada a api
            moviePage.movies = apiMovies
            tvShowPage.tvShows = apiTvShows

            // definir valor de retorno necesarios
            Mockito.`when`(mockTmdbApi.getTopRatedMovies(API_KEY)).thenReturn(moviePage)
            Mockito.`when`(mockTmdbApi.getPopularMovies(API_KEY)).thenReturn(moviePage)
            Mockito.`when`(mockTmdbApi.getUpcomingMovies(API_KEY)).thenReturn(moviePage)
            Mockito.`when`(mockTmdbApi.getTopRatedTvShows(API_KEY)).thenReturn(tvShowPage)
            Mockito.`when`(mockTmdbApi.getPopularTvShows(API_KEY)).thenReturn(tvShowPage)
            Mockito.`when`(mockTmdbApi.getAiringTodayTvShows(API_KEY)).thenReturn(tvShowPage)

            // ejecutar metodo a probar
            repository.refreshData()

            // validar numero de llamadas a los metodos dentro del metodo a probar
            verify(mockTmdbApi, times(1)).getTopRatedMovies(API_KEY)
            verify(mockTmdbApi, times(1)).getPopularMovies(API_KEY)
            verify(mockTmdbApi, times(1)).getUpcomingMovies(API_KEY)
            verify(mockTmdbApi, times(1)).getTopRatedTvShows(API_KEY)
            verify(mockTmdbApi, times(1)).getPopularTvShows(API_KEY)
            verify(mockTmdbApi, times(1)).getAiringTodayTvShows(API_KEY)
            verify(mockMovieDao, times(1))
                .clear(MovieType.TOP_RATED.value)
            verify(mockMovieDao, times(1))
                .insertAll(topRatedMovies)
            verify(mockMovieDao, times(1))
                .clear(MovieType.POPULAR.value)
            verify(mockMovieDao, times(1))
                .insertAll(popularMovies)
            verify(mockMovieDao, times(1))
                .clear(MovieType.UPCOMING.value)
            verify(mockMovieDao, times(1))
                .insertAll(upcomingMovies)
            verify(mockTvShowDao, times(1))
                .clear(TvShowType.TOP_RATED.value)
            verify(mockTvShowDao, times(1))
                .insertAll(topRatedTvShows)
            verify(mockTvShowDao, times(1))
                .clear(TvShowType.POPULAR.value)
            verify(mockTvShowDao, times(1))
                .insertAll(popularTvShows)
            verify(mockTvShowDao, times(1))
                .clear(TvShowType.AIRING_TODAY.value)
            verify(mockTvShowDao, times(1))
                .insertAll(airingTodayTvShows)
        }
    }
}