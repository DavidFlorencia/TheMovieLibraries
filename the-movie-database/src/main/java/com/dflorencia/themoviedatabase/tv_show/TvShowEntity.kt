package com.dflorencia.themoviedatabase.tv_show

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows")
data class TvShowEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String? = "",
    @ColumnInfo(name = "first_air_date") val firstAirDate: String? = "",
    val name: String? = "",
    @ColumnInfo(name = "original_language") val originalLanguage: String? = "",
    @ColumnInfo(name = "original_name") val originalName: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    @ColumnInfo(name = "poster_path") val posterPath: String? = "",
    @ColumnInfo(name = "vote_average") var voteAverage: Double? = 0.0,
    @ColumnInfo(name = "vote_count") var voteCount: Int? = 0,
    @ColumnInfo(name = "type") val type: String? = ""
)