package com.dflorencia.themovieapi.tv_show

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    @Json(name = "backdrop_path") var backdropPath: String? = null,
    @Json(name = "first_air_date") var firstAirDate: String? = null,
    @Json(name = "genre_ids") var genreIds: List<Int> = arrayListOf(),
    @Json(name = "id") var id: Int? = null,
    @Json(name = "name") var name: String? = null,
    @Json(name = "origin_country") var originCountry: List<String> = arrayListOf(),
    @Json(name = "original_language") var originalLanguage: String? = null,
    @Json(name = "original_name") var originalName: String? = null,
    @Json(name = "overview") var overview: String? = null,
    @Json(name = "popularity") var popularity: Double? = null,
    @Json(name = "poster_path") var posterPath: String? = null,
    @Json(name = "vote_average") var voteAverage: Double? = null,
    @Json(name = "vote_count") var voteCount: Int? = null
): Parcelable