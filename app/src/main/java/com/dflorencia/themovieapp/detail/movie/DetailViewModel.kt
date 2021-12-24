package com.dflorencia.themovieapp.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflorencia.themovieapi.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val appRepository: com.dflorencia.themovierepository.AppRepository): ViewModel(){

    private val _movie = MutableLiveData<Movie>();
    val movie: LiveData<Movie> get() = _movie
    val trailerKey: LiveData<String> get() = appRepository.key

    fun setMovie(movie: Movie){
        _movie.value = movie
    }

    fun refreshDataFromRepository(){
        viewModelScope.launch {
            appRepository.refreshMovieTrailerKey(movie.value?.id.toString())
        }
    }

}