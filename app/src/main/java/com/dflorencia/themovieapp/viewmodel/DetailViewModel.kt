package com.dflorencia.themovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflorencia.api.Movie
import com.dflorencia.themovieapp.root.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val appRepository: AppRepository): ViewModel(){

    private val _movie = MutableLiveData<com.dflorencia.api.Movie>();
    val movie: LiveData<com.dflorencia.api.Movie> get() = _movie
    val trailerKey: LiveData<String> get() = appRepository.key

    fun setMovie(movie: com.dflorencia.api.Movie){
        _movie.value = movie
    }

    fun refreshDataFromRepository(){
        viewModelScope.launch {
            appRepository.refreshMovieTrailerKey(movie.value?.id.toString())
        }
    }

}