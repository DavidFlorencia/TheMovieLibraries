package com.dflorencia.themovieapi.movie

import com.squareup.moshi.Json

class MoviePage {
    @Json(name = "page")
    var page: Int? = null

    @Json(name = "results")
    var movies: List<Movie>? = listOf()

    @Json(name = "total_pages")
    var totalPages: Int? = null

    @Json(name = "total_results")
    var totalResults: Int? = null
}