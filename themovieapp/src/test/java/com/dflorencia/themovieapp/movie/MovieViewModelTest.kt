package com.dflorencia.themovieapp.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapp.getOrAwaitValue
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MovieViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel
    private val MOVIE = Movie(id = 2343, title = "Detail Movie")

    @Test
    fun setMovieTest(){
        movieViewModel = MovieViewModel()
        Assert.assertNull(movieViewModel.movie.value)
        movieViewModel.setMovie(MOVIE)
        Assert.assertNotNull(movieViewModel.movie.getOrAwaitValue())
        Assert.assertTrue(movieViewModel.movie.getOrAwaitValue()==MOVIE)
    }
}