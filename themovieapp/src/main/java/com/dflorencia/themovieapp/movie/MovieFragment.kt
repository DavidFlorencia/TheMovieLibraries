package com.dflorencia.themovieapp.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dflorencia.themovieapp.MainActivity
import com.dflorencia.themovieapp.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

class MovieFragment:Fragment() {

    private val args: MovieFragmentArgs by navArgs()
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setMovie(args.movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if(requireActivity() is MainActivity){
            (activity as AppCompatActivity).supportActionBar?.title = args.movie.title
        }
    }
}