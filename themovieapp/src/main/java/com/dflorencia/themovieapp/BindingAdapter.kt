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

@BindingAdapter("videoUrl")
fun setVideoUrl(webView: WebView, key: String?) {
    key?.let {
        val url = "https://www.youtube.com/embed/$key"
        val dataUrl = "<html>" +
                "<head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "</head>" +
                "<body>" +
                "<br>" +
                "<iframe width=\"100%\" height=\"100%\" src=\"" +
                url +
                "\" frameborder=\"0\" allowfullscreen/>" +
                "</body>" +
                "</html>"

        webView.loadData(dataUrl, "text/html", "utf-8")
    }
}