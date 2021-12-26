package com.dflorencia.themovieapp.movie

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapp.R
import com.dflorencia.themovieapp.checkViewIsDisplayedAndWithText
import com.dflorencia.themovieapp.checkViewIsDisplayedAndWithTextId
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MovieFragmentTest {
    private val ORIGINAL_TITLE = "titulo original"
    private val ORIGINAL_LANGUAJE = "lenguaje origianl"
    private val RELEASE_DATA = "fecha de estreno"
    private val POPULARITY = 63.12
    private val VOTE_COUNT = 235
    private val VOTE_AVERAGE = 32.2
    private val OVERVIEW = "resumen"

    private val movie = Movie(
        id = 123,
        title = "Fake Movie",
        originalTitle = ORIGINAL_TITLE,
        originalLanguage = ORIGINAL_LANGUAJE,
        releaseDate = RELEASE_DATA,
        popularity = POPULARITY,
        voteCount = VOTE_COUNT,
        voteAverage = VOTE_AVERAGE,
        overview = OVERVIEW
    )

    @Test
    fun launchMovieFragment_checkVisibilityAndData() = runTest {
        val bundle = MovieFragmentArgs(movie).toBundle()
        launchFragmentInContainer<MovieFragment>(bundle, R.style.Theme_TheMovieApp)

        val mapTextId = mapOf(
            R.id.lblOriginalTitle to R.string.original_title,
            R.id.lblOriginalLanguage to R.string.original_language,
            R.id.lblReleaseDate to R.string.release_date,
            R.id.lblPopularity to R.string.popularity,
            R.id.lblVote to R.string.vote,
            R.id.lblOverview to R.string.overview,
        )

        for (map in mapTextId)
            checkViewIsDisplayedAndWithTextId(map.key,map.value)

        val mapText = mapOf(
            R.id.txtOriginalTitle to ORIGINAL_TITLE,
            R.id.txtOriginalLanguage to ORIGINAL_LANGUAJE,
            R.id.txtReleaseDate to RELEASE_DATA,
            R.id.txtPopularity to POPULARITY.toString(),
            R.id.txtVote to "$VOTE_COUNT/$VOTE_AVERAGE",
            R.id.txtOverview to OVERVIEW
        )

        for (map in mapText)
            checkViewIsDisplayedAndWithText(map.key,map.value)
    }
}