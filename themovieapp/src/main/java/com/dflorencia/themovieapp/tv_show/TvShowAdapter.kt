package com.dflorencia.themovieapp.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dflorencia.themovieapi.tv_show.TvShow
import com.dflorencia.themovieapp.R
import com.dflorencia.themovieapp.databinding.ItemTvShowBinding

class TvShowAdapter(private val clickListener: TvShowClickListener):
    ListAdapter<TvShow, TvShowAdapter.TvShowViewHolder>(ItemDiffCallback()){

    class TvShowViewHolder private constructor(val binding: ItemTvShowBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(tvShow: TvShow, clickListener: TvShowClickListener){
            binding.tvShow = tvShow
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TvShowViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTvShowBinding.inflate(layoutInflater, parent, false)
                return TvShowViewHolder(binding)
            }
        }
    }

    class ItemDiffCallback: DiffUtil.ItemCallback<TvShow>(){
        override fun areItemsTheSame(oldTvShow: TvShow, newTvShow: TvShow): Boolean {
            return oldTvShow === newTvShow
        }

        override fun areContentsTheSame(oldTvShow: TvShow, newTvShow: TvShow): Boolean {
            return oldTvShow.id == newTvShow.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)

        val animation: Animation = AnimationUtils.loadAnimation(
            holder.binding.root.context,
            R.anim.down_from_top
        )
        holder.itemView.startAnimation(animation)
    }

    override fun onViewDetachedFromWindow(holder: TvShowViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.binding.root.clearAnimation()
    }

    class TvShowClickListener(val clickListener: (tvShow: TvShow) -> Unit){
        fun onClick(tvShow: TvShow) = clickListener(tvShow)
    }
}