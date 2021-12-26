package com.dflorencia.themoviedatabase.tv_show

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows")
data class TvShowEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String? = null,
    @ColumnInfo(name = "first_air_date") val firstAirDate: String? = null,
    val name: String? = null,
    @ColumnInfo(name = "original_language") val originalLanguage: String? = null,
    @ColumnInfo(name = "original_name") val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @ColumnInfo(name = "poster_path") val posterPath: String? = null,
    @ColumnInfo(name = "vote_average") var voteAverage: Double? = null,
    @ColumnInfo(name = "vote_count") var voteCount: Int? = null,
    @ColumnInfo(name = "type") val type: String? = null
)