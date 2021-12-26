package com.dflorencia.themovieapp.tv_show

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapi.tv_show.TvShow
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
class TvShowFragmentTest {
    private val ORIGINAL_NAME = "nombre original"
    private val ORIGINAL_LANGUAJE = "lenguaje origianl"
    private val FIRST_AIR_DATE = "fecha de estreno"
    private val POPULARITY = 63.12
    private val VOTE_COUNT = 235
    private val VOTE_AVERAGE = 32.2
    private val OVERVIEW = "resumen"

    private val tvShow = TvShow(
        id = 123,
        name = "Fake Tv Show",
        originalName = ORIGINAL_NAME,
        originalLanguage = ORIGINAL_LANGUAJE,
        firstAirDate = FIRST_AIR_DATE,
        popularity = POPULARITY,
        voteCount = VOTE_COUNT,
        voteAverage = VOTE_AVERAGE,
        overview = OVERVIEW
    )

    @Test
    fun launchMovieFragment_checkVisibilityAndData() = runTest {
        val bundle = TvShowFragmentArgs(tvShow).toBundle()
        launchFragmentInContainer<TvShowFragment>(bundle, R.style.Theme_TheMovieApp)

        val mapTextId = mapOf(
            R.id.lblOriginalName to R.string.original_name,
            R.id.lblOriginalLanguage to R.string.original_language,
            R.id.lblFirstAirDate to R.string.first_air_date,
            R.id.lblPopularity to R.string.popularity,
            R.id.lblVote to R.string.vote,
            R.id.lblOverview to R.string.overview,
        )

        for (map in mapTextId)
            checkViewIsDisplayedAndWithTextId(map.key,map.value)

        val mapText = mapOf(
            R.id.txtOriginalTitle to ORIGINAL_NAME,
            R.id.txtOriginalLanguage to ORIGINAL_LANGUAJE,
            R.id.txtFirstAirDate to FIRST_AIR_DATE,
            R.id.txtPopularity to POPULARITY.toString(),
            R.id.txtVote to "$VOTE_COUNT/$VOTE_AVERAGE",
            R.id.txtOverview to OVERVIEW
        )

        for (map in mapText)
            checkViewIsDisplayedAndWithText(map.key,map.value)
    }
}