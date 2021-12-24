package com.dflorencia.themovieapi.movie

import com.dflorencia.themovieapi.movie.MovieTrailer
import com.squareup.moshi.Json

class MovieTrailerPage {
    @Json(name = "id")
    var id: Int? = null

    @Json(name = "results")
    var movieTrailers: List<MovieTrailer>? = null
}