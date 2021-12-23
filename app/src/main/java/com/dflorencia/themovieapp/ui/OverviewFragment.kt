package com.dflorencia.themovieapp.ui

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dflorencia.themovieapp.viewmodel.Filter
import com.dflorencia.themovieapp.viewmodel.OverviewViewModel
import com.dflorencia.themovieapp.R
import com.dflorencia.themovieapp.api.Movie
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

        viewModel.filter.observe(viewLifecycleOwner){
            (activity as AppCompatActivity).supportActionBar?.title = when (it){
                Filter.TOP_RATED -> {
                    getString(R.string.top_rated_movies)
                }
                Filter.POPULAR -> {
                    getString(R.string.popular_movies)
                }
                Filter.SEARCH -> getString(R.string.found_movies)
                else -> {
                    setHasOptionsMenu(false)
                    getString(R.string.cache_movies)
                }
            }
        }

        binding.schMovies?.onActionViewExpanded();
        binding.schMovies?.clearFocus();
        binding.schMovies?.setOnQueryTextListener(queryTextChanged);

        setHasOptionsMenu(true)

        return binding.root
    }

    private val queryTextChanged: SearchView.OnQueryTextListener = object: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                viewModel.setFilter(Filter.SEARCH, it)
            }
            return true;
        }

        override fun onQueryTextChange(query: String?): Boolean {
            query?.let {
                if (query.isEmpty()){
                    viewModel.setLastFilter()
                }
            }
            return true;
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.itemTopRated -> {
                viewModel.setFilter(Filter.TOP_RATED)
            }
            R.id.itemPopular -> {
                viewModel.setFilter(Filter.POPULAR)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun navigateToMovieDetail(movie: Movie) {
        val direction = OverviewFragmentDirections.actionOverviewToDetail(movie)
        findNavController().navigate(direction)
    }
}