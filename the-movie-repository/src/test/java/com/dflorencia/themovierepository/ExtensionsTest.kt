package com.dflorencia.themovierepository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapi.tv_show.TvShow
import com.dflorencia.themoviedatabase.movie.MovieEntity
import com.dflorencia.themoviedatabase.movie.MovieType
import com.dflorencia.themoviedatabase.tv_show.TvShowEntity
import com.dflorencia.themoviedatabase.tv_show.TvShowType
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ExtensionsTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val MOVIE_ID_1 = 111
    val MOVIE_ID_2 = 112
    val MOVIE_TITLE_1 = "Movie1"
    val MOVIE_TITLE_2 = "Movie2"

    private val apiMovies = listOf(
        Movie(id = MOVIE_ID_1, title = MOVIE_TITLE_1),
        Movie(id = MOVIE_ID_2, title = MOVIE_TITLE_2)
    )

    private val topRatedMovies = listOf(
        MovieEntity(id = MOVIE_ID_1, title = MOVIE_TITLE_1, type = MovieType.TOP_RATED.value),
        MovieEntity(id = MOVIE_ID_2, title = MOVIE_TITLE_2, type = MovieType.TOP_RATED.value),
    )

    private val popularMovies = listOf(
        MovieEntity(id = MOVIE_ID_1, title = MOVIE_TITLE_1, type = MovieType.POPULAR.value),
        MovieEntity(id = MOVIE_ID_2, title = MOVIE_TITLE_2, type = MovieType.POPULAR.value),
    )

    private val upcomingMovies = listOf(
        MovieEntity(id = MOVIE_ID_1, title = MOVIE_TITLE_1, type = MovieType.UPCOMING.value),
        MovieEntity(id = MOVIE_ID_2, title = MOVIE_TITLE_2, type = MovieType.UPCOMING.value),
    )

    val TV_SHOW_ID_1 = 111
    val TV_SHOW_ID_2 = 112
    val TV_SHOW_TITLE_1 = "Movie1"
    val TV_SHOW_TITLE_2 = "Movie2"

    private val apiTvShows = listOf(
        TvShow(id = TV_SHOW_ID_1, name = TV_SHOW_TITLE_1),
        TvShow(id = TV_SHOW_ID_2, name = TV_SHOW_TITLE_2)
    )

    private val topRatedTvShows = listOf(
        TvShowEntity(id = TV_SHOW_ID_1, name = TV_SHOW_TITLE_1, type = TvShowType.TOP_RATED.value),
        TvShowEntity(id = TV_SHOW_ID_2, name = TV_SHOW_TITLE_2, type = TvShowType.TOP_RATED.value),
    )

    private val popularTvShows = listOf(
        TvShowEntity(id = TV_SHOW_ID_1, name = TV_SHOW_TITLE_1, type = TvShowType.POPULAR.value),
        TvShowEntity(id = TV_SHOW_ID_2, name = TV_SHOW_TITLE_2, type = TvShowType.POPULAR.value),
    )

    private val airingTodayTvShows = listOf(
        TvShowEntity(id = TV_SHOW_ID_1, name = TV_SHOW_TITLE_1, type = TvShowType.AIRING_TODAY.value),
        TvShowEntity(id = TV_SHOW_ID_2, name = TV_SHOW_TITLE_2, type = TvShowType.AIRING_TODAY.value),
    )

    @Test
    fun assertTopRatedMovies2ApiMovies(){
        Assert.assertTrue(topRatedMovies.asApiModel() == apiMovies)
    }

    @Test
    fun assertApiMovies2TopRatedMovies(){
        Assert.assertTrue(apiMovies.asDatabaseModel(MovieType.TOP_RATED) == topRatedMovies)
    }

    @Test
    fun assertPopularMovies2ApiMovies(){
        Assert.assertTrue(popularMovies.asApiModel() == apiMovies)
    }

    @Test
    fun assertApiMovies2PopularMovies(){
        Assert.assertTrue(apiMovies.asDatabaseModel(MovieType.POPULAR) == popularMovies)
    }

    @Test
    fun assertUpcomingMovies2ApiMovies(){
        Assert.assertTrue(upcomingMovies.asApiModel() == apiMovies)
    }

    @Test
    fun assertApiMovies2UpcomingMovies(){
        Assert.assertTrue(apiMovies.asDatabaseModel(MovieType.UPCOMING) == upcomingMovies)
    }

    @Test
    fun assertTopRatedTvShows2ApiTvShows(){
        Assert.assertTrue(topRatedTvShows.asApiModel() == apiTvShows)
    }

    @Test
    fun assertApiTvShows2TopRatedTvShows(){
        Assert.assertTrue(apiTvShows.asDatabaseModel(TvShowType.TOP_RATED) == topRatedTvShows)
    }

    @Test
    fun assertPopularTvShows2ApiTvShows(){
        Assert.assertTrue(popularTvShows.asApiModel() == apiTvShows)
    }

    @Test
    fun assertApiTvShows2PopularTvShows(){
        Assert.assertTrue(apiTvShows.asDatabaseModel(TvShowType.POPULAR) == popularTvShows)
    }

    @Test
    fun assertAiringTodayTvShows2ApiTvShows(){
        Assert.assertTrue(airingTodayTvShows.asApiModel() == apiTvShows)
    }

    @Test
    fun assertApiTvShows2AiringTodayTvShows(){
        Assert.assertTrue(apiTvShows.asDatabaseModel(TvShowType.AIRING_TODAY) == airingTodayTvShows)
    }
}