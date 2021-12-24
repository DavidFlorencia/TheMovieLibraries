package com.dflorencia.api

import com.squareup.moshi.Json

class TvShowPage {
    @Json(name = "page")
    var page: Int? = null

    @Json(name = "results")
    var tvShows: List<TvShow>? = listOf()

    @Json(name = "total_pages")
    var totalPages: Int? = null

    @Json(name = "total_results")
    var totalResults: Int? = null
}