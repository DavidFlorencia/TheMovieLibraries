package com.dflorencia.themovieapp.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dflorencia.themovieapp.R
import com.dflorencia.themovieapp.databinding.FragmentDetailBinding
import com.dflorencia.themovieapp.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment:Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setMovie(args.movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.movie.observe(viewLifecycleOwner){
            (activity as AppCompatActivity).supportActionBar?.title = it.title
            if (isConnectedOrConnecting())
                viewModel.refreshDataFromRepository()
        }

        val webSettings = binding.wvViewPlayer.settings

        webSettings.javaScriptEnabled = true
        binding.wvViewPlayer.settings.loadWithOverviewMode = true
        binding.wvViewPlayer.settings.useWideViewPort = true

        binding.imgPlayIcon.setOnClickListener {
            val test = "https://www.youtube.com/watch?v=${viewModel.trailerKey.value}"
            Log.d("Prueba",test)
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse(test))

            startActivity(intent)
        }

        return binding.root
    }

    private fun isConnectedOrConnecting(): Boolean {
        val cm = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.detail_from_movie)
    }
}