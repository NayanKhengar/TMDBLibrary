package com.nayan.tmdbmoviesdemo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nayan.networksdk.data.model.Movie
import com.nayan.tmdbmoviesdemo.databinding.ItemMovieRecommendationsBinding
import com.squareup.picasso.Picasso

class MovieRecommendationsAdapter :
    RecyclerView.Adapter<MovieRecommendationsAdapter.MovieViewHolder>() {

    private var movies: List<Movie> = listOf()

    fun submitList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemMovieRecommendationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(private val binding: ItemMovieRecommendationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${movie.backdrop_path}")
                .into(binding.ivProfile)
            binding.title.text = movie.title
            binding.popular.text = (movie.vote_average?.times(10))?.toInt().toString() +"%"
        }
    }
}
