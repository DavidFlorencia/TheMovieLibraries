package com.dflorencia.themovieapp.overview

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapi.tv_show.TvShow
import com.dflorencia.themovierepository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

enum class ApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class OverviewViewModel @Inject constructor(val appRepository: AppRepository): ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> get() = _status;

    val moviesTopRated: LiveData<List<Movie>> get() = appRepository.moviesTopRated
    val moviesPopular: LiveData<List<Movie>> get() = appRepository.moviesPopular
    val moviesUpcoming: LiveData<List<Movie>> get() = appRepository.moviesUpcoming
    val tvShowsTopRated: LiveData<List<TvShow>> get() = appRepository.tvShowsTopRated
    val tvShowsPopular: LiveData<List<TvShow>> get() = appRepository.tvShowsPopular
    val tvShowsAiringToday: LiveData<List<TvShow>> get() = appRepository.tvShowsAiringToday

    init {
        refreshDataFromRepository()
    }

    @VisibleForTesting
    fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                appRepository.refreshData()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                if (moviesTopRated.value.isNullOrEmpty()) {
                    _status.value = ApiStatus.ERROR
                }else {
                    _status.value = ApiStatus.DONE
                }
            }
        }
    }
}