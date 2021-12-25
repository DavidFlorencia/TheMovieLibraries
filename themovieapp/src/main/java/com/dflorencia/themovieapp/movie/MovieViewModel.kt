package com.dflorencia.themovieapp.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dflorencia.themovieapi.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel: ViewModel(){
    private val _movie = MutableLiveData<Movie>();
    val movie: LiveData<Movie> get() = _movie

    fun setMovie(movie: Movie){
        _movie.value = movie
    }
}