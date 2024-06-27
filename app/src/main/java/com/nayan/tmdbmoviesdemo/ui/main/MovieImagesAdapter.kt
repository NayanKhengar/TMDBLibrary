package com.nayan.tmdbmoviesdemo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nayan.tmdbmoviesdemo.data.model.Backdrop
import com.nayan.tmdbmoviesdemo.databinding.ItemMovieImagesBinding
import com.squareup.picasso.Picasso

class MovieImagesAdapter : RecyclerView.Adapter<MovieImagesAdapter.MovieViewHolder>() {

    private var movies: List<Backdrop?>? = listOf()

    fun submitList(movies: List<Backdrop?>?) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemMovieImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies?.get(position))
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    inner class MovieViewHolder(private val binding: ItemMovieImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Backdrop?) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${movie?.filePath}")
                .into(binding.ivBanner)
        }
    }
}
