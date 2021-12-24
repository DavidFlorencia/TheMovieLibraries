package com.dflorencia.themovieapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dflorencia.themovieapp.viewmodel.OverviewViewModel
import com.dflorencia.themovieapp.R
import com.dflorencia.api.Movie
import com.dflorencia.themovieapp.databinding.FragmentOverviewBinding
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

        binding.rvItems.adapter = MovieAdapter(MovieAdapter.MovieClickListener {
            navigateToMovieDetail(it)
        })

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        return binding.root
    }

    private fun navigateToMovieDetail(movie: com.dflorencia.api.Movie) {
        val direction = OverviewFragmentDirections.actionOverviewToDetail(movie)
        findNavController().navigate(direction)
    }
}