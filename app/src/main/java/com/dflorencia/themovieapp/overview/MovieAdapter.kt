package com.dflorencia.themovieapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapp.R
import com.dflorencia.themovieapp.databinding.ItemViewBinding

class MovieAdapter(private val clickListener: MovieClickListener): ListAdapter<Movie, MovieAdapter.MovieViewHolder>(
    ItemDiffCallback()
) {

    private var lastPosition = -1

    class MovieViewHolder private constructor(val binding: ItemViewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie, clickListener: MovieClickListener){
            binding.movie = movie;
            binding.clickListener = clickListener;
            binding.executePendingBindings();
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context);
                val binding = ItemViewBinding.inflate(layoutInflater, parent, false);
                return MovieViewHolder(binding);
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
        val item = getItem(position) as Movie
        holder.bind(item, clickListener)

        val animation: Animation = AnimationUtils.loadAnimation(
            holder.binding.root.context,
            if (position > lastPosition) R.anim.up_from_bottom else R.anim.down_from_top
        )
        holder.itemView.startAnimation(animation)
        lastPosition = position
    }

    override fun onViewDetachedFromWindow(holder: MovieViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.binding.root.clearAnimation()
    }

    class MovieClickListener(val clickListener: (movie: Movie) -> Unit){
        fun onClick(movie: Movie) = clickListener(movie);
    }
}