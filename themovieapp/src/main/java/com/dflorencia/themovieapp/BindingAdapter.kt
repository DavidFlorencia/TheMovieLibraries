package com.dflorencia.themovieapp

import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dflorencia.themovieapi.movie.Movie
import com.dflorencia.themovieapi.tv_show.TvShow
import com.dflorencia.themovieapp.movie.MovieAdapter
import com.dflorencia.themovieapp.overview.ApiStatus
import com.dflorencia.themovieapp.tv_show.TvShowAdapter

@BindingAdapter("visible")
fun setVisibility(view: View, visibility: Boolean){
    view.visibility = if (visibility) View.VISIBLE else View.GONE;
}

@BindingAdapter("dataMovie")
fun setDataMovie(recyclerView: RecyclerView, data: List<Movie>?){
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("dataTvShow")
fun setDataTvShow(recyclerView: RecyclerView, data: List<TvShow>?){
    val adapter = recyclerView.adapter as TvShowAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        val baseUrl = imageView.context.getString(R.string.base_url_images)

        val absolutUrl = baseUrl + url

        Glide.with(imageView.context)
            .load(absolutUrl)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken))
            .into(imageView)

        imageView.scaleType = ImageView.ScaleType.FIT_XY
    } ?: run {
        imageView.setImageResource(R.drawable.ic_broken)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER;
    }
}

@BindingAdapter("apiStatus")
fun setStatus(statusImageView: ImageView,
               status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}