package com.dflorencia.themoviedatabase.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean? = null,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String? = null,
    @ColumnInfo(name = "original_language") val originalLanguage: String? = null,
    @ColumnInfo(name = "original_title") val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @ColumnInfo(name = "poster_path") val posterPath: String? = null,
    @ColumnInfo(name = "release_date") val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    @ColumnInfo(name = "vote_average") val voteAverage: Double? = null,
    @ColumnInfo(name = "vote_count") val voteCount: Int? = null,
    @ColumnInfo(name = "type") val type: String? = null
)