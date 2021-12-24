package com.dflorencia.themovierepository

import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapi.tv_show.TvShow
import com.dflorencia.themoviedatabase.movie.MovieEntity
import com.dflorencia.themoviedatabase.tv_show.TvShowEntity
import com.dflorencia.themovierepository.enums.MovieType
import com.dflorencia.themovierepository.enums.TvShowType

@JvmName("movieDatabase2Api")
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

@JvmName("movieApi2Database")
fun List<Movie>.asDatabaseModel(type: MovieType): List<MovieEntity> {
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
            type = type.value
        )
    }
}

@JvmName("tvShowDatabase2Api")
fun List<TvShowEntity>.asApiModel(): List<TvShow>{
    return map {
        val tvShow = TvShow()
        tvShow.backdropPath = it.backdropPath
        tvShow.firstAirDate = it.firstAirDate
        tvShow.id = it.id
        tvShow.name = it.name
        tvShow.originalLanguage = it.originalLanguage
        tvShow.originalName = it.originalName
        tvShow.overview = it.overview
        tvShow.popularity = it.popularity
        tvShow.posterPath = it.posterPath
        tvShow.voteAverage = it.voteAverage
        tvShow.voteCount = it.voteCount
        tvShow
    }
}

@JvmName("tvShowApi2Database")
fun List<TvShow>.asDatabaseModel(type: TvShowType): List<TvShowEntity> {
    return map {
        TvShowEntity(
            id = it.id!!,
            backdropPath = it.backdropPath,
            firstAirDate = it.firstAirDate,
            name = it.name,
            originalLanguage = it.originalLanguage,
            originalName = it.originalName,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            type = type.value
        )
    }
}