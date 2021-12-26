package com.dflorencia.themovieapp.tv_show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dflorencia.themovieapi.tv_show.TvShow
import com.dflorencia.themovieapp.getOrAwaitValue
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class TvShowViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var tvShowViewModel: TvShowViewModel
    private val TV_SHOW = TvShow(id = 2343, name = "Detail Tv Show")

    @Test
    fun setTvShowTest(){
        tvShowViewModel = TvShowViewModel()
        Assert.assertNull(tvShowViewModel.tvShow.value)
        tvShowViewModel.setTvShow(TV_SHOW)
        Assert.assertNotNull(tvShowViewModel.tvShow.getOrAwaitValue())
        Assert.assertTrue(tvShowViewModel.tvShow.getOrAwaitValue()==TV_SHOW)
    }
}