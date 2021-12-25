package com.dflorencia.themovieapp.overview

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapi.tv_show.TvShow
import com.dflorencia.themovieapp.R
import com.dflorencia.themovieapp.databinding.FragmentOverviewBinding
import com.dflorencia.themovieapp.movie.MovieAdapter
import com.dflorencia.themovieapp.tv_show.TvShowAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment: Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.rvMoviesTopRated.adapter = MovieAdapter(MovieAdapter.MovieClickListener {
            navigateToMovieDetail(it)
        })

        binding.rvMoviesPopular.adapter = MovieAdapter(MovieAdapter.MovieClickListener {
            navigateToMovieDetail(it)
        })

        binding.rvMoviesUpcoming.adapter = MovieAdapter(MovieAdapter.MovieClickListener {
            navigateToMovieDetail(it)
        })

        binding.rvTvShowsTopRated.adapter = TvShowAdapter(TvShowAdapter.TvShowClickListener {
            navigateToTvShowDetail(it)
        })

        binding.rvTvShowsPopular.adapter = TvShowAdapter(TvShowAdapter.TvShowClickListener {
            navigateToTvShowDetail(it)
        })

        binding.rvTvShowsAiringToday.adapter = TvShowAdapter(TvShowAdapter.TvShowClickListener {
            navigateToTvShowDetail(it)
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    private fun navigateToMovieDetail(movie: Movie) {
        val direction = OverviewFragmentDirections.actionOverviewToMovie(movie)
        findNavController().navigate(direction)
    }

    private fun navigateToTvShowDetail(tvShow: TvShow) {
        val direction = OverviewFragmentDirections.actionOverviewToTvShow(tvShow)
        findNavController().navigate(direction)
    }
}