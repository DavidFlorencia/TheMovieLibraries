package com.dflorencia.themovieapp.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapp.R
import com.dflorencia.themovieapp.databinding.ItemMovieBinding

class MovieAdapter(private val clickListener: MovieClickListener):
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(ItemDiffCallback()) {

    class MovieViewHolder private constructor(val binding: ItemMovieBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie, clickListener: MovieClickListener){
            binding.movie = movie
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }
    }

    class ItemDiffCallback: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldMovie: Movie, newMovie: Movie): Boolean {
            return oldMovie === newMovie
        }

        override fun areContentsTheSame(oldMovie: Movie, newMovie: Movie): Boolean {
            return oldMovie.id == newMovie.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)

        val animation: Animation = AnimationUtils.loadAnimation(
            holder.binding.root.context,
            R.anim.down_from_top
        )
        holder.itemView.startAnimation(animation)
    }

    override fun onViewDetachedFromWindow(holder: MovieViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.binding.root.clearAnimation()
    }

    class MovieClickListener(val clickListener: (movie: Movie) -> Unit){
        fun onClick(movie: Movie) = clickListener(movie)
    }
}