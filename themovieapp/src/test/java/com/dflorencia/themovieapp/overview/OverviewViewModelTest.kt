package com.dflorencia.themovieapp.overview

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapp.MainCoroutineRule
import com.dflorencia.themovieapp.getOrAwaitValue
import com.dflorencia.themovierepository.AppRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import java.io.IOException
import java.lang.IllegalArgumentException

class OverviewViewModelTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var overviewViewModel: OverviewViewModel

    private lateinit var mockRepository: AppRepository

    @Before
    fun setup(){
        mockRepository = Mockito.mock(AppRepository::class.java)
    }

    @Test
    fun initOverview_whenRefreshDataSucces_ApiStatusDone(){
        mainCoroutineRule.runBlockingTest {
            overviewViewModel = OverviewViewModel(mockRepository)
            verify(mockRepository, times(1)).refreshData()
            val status = overviewViewModel.status.getOrAwaitValue()
            assert(status == ApiStatus.DONE)
        }
    }

    @Test
    fun initOverview_whenRefreshDataFailAndListIsNull_ApiStatusError(){
        mainCoroutineRule.runBlockingTest {
            Mockito.doThrow(IllegalArgumentException()).`when`(mockRepository).refreshData()

            Mockito.`when`(mockRepository.moviesTopRated)
                .thenReturn(MutableLiveData(null))

            overviewViewModel = OverviewViewModel(mockRepository)

            val status = overviewViewModel.status.getOrAwaitValue()
            assert(status == ApiStatus.ERROR)
        }
    }

    @Test
    fun initOverview_whenRefreshDataFailAndListIsEmpty_ApiStatusError(){
        mainCoroutineRule.runBlockingTest {
            Mockito.doThrow(IllegalArgumentException()).`when`(mockRepository).refreshData()

            Mockito.`when`(mockRepository.moviesTopRated)
                .thenReturn(MutableLiveData(listOf()))

            overviewViewModel = OverviewViewModel(mockRepository)

            mockRepository.moviesTopRated.value.isNullOrEmpty()

            val status = overviewViewModel.status.getOrAwaitValue()
            assert(status == ApiStatus.ERROR)
        }
    }

    @Test
    fun initOverview_whenRefreshDataFailButListIsNotNullOrEmpty_ApiStatusError(){
        mainCoroutineRule.runBlockingTest {
            Mockito.doThrow(IllegalArgumentException()).`when`(mockRepository).refreshData()

            Mockito.`when`(mockRepository.moviesTopRated)
                .thenReturn(MutableLiveData(listOf(Movie(id = 123))))

            overviewViewModel = OverviewViewModel(mockRepository)

            mockRepository.moviesTopRated.value.isNullOrEmpty()

            val status = overviewViewModel.status.getOrAwaitValue()
            assert(status == ApiStatus.DONE)
        }
    }
}