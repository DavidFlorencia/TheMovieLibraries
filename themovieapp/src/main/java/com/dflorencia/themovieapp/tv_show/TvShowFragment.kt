package com.dflorencia.themovieapp.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dflorencia.themovieapp.MainActivity
import com.dflorencia.themovieapp.databinding.FragmentTvShowBinding

class TvShowFragment:Fragment() {

    private val args: TvShowFragmentArgs by navArgs()
    private val viewModel: TvShowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setTvShow(args.tvShow)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTvShowBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if(requireActivity() is MainActivity){
            (activity as AppCompatActivity).supportActionBar?.title = args.tvShow.name
        }
    }
}