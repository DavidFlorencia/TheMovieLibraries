package com.dflorencia.themovieapp.tv_show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dflorencia.themovieapi.tv_show.TvShow

class TvShowViewModel: ViewModel(){
    private val _tvShow = MutableLiveData<TvShow>();
    val tvShow: LiveData<TvShow> get() = _tvShow

    fun setTvShow(tvShow: TvShow){
        _tvShow.value = tvShow
    }
}