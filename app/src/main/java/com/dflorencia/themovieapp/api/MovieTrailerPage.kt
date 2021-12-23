package com.dflorencia.themovieapp.api

import com.dflorencia.themovieapp.api.MovieTrailer
import com.squareup.moshi.Json

class MovieTrailerPage {
    @Json(name = "id")
    var id: Int? = null

    @Json(name = "results")
    var movieTrailers: List<MovieTrailer>? = null
}