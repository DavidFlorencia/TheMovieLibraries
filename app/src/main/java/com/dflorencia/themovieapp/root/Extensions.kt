package com.dflorencia.themovieapp.root

import com.dflorencia.api.Movie
import com.dflorencia.themovieapp.database.MovieEntity

fun List<MovieEntity>.asApiModel(): List<Movie>{
    return map {
        val movie = Movie()
        movie.adult = it.adult
        movie.backdropPath = it.backdropPath
        movie.id = it.id
        movie.originalLanguage = it.originalLanguage
        movie.originalTitle = it.originalTitle
        movie.overview = it.overview
        movie.popularity = it.popularity
        movie.posterPath = it.posterPath
        movie.releaseDate = it.releaseDate
        movie.title = it.title
        movie.video = it.video
        movie.voteAverage = it.voteAverage
        movie.voteCount = it.voteCount
        movie
    }
}

fun List<Movie>.asDatabaseModel(type: String): List<MovieEntity> {
    return map {
        MovieEntity(
            id = it.id!!,
            adult = it.adult,
            backdropPath = it.backdropPath,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            video = it.video,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            type = type
        )
    }
}