package com.nayan.tmdbmoviesdemo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nayan.networksdk.data.model.Movie
import com.nayan.tmdbmoviesdemo.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies: List<Movie> = listOf()
    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun submitList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.title.text = movie.title
            binding.date.text = formatDateString(movie.release_date)
            binding.overview.text = movie.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500${movie.poster_path}").into(binding.poster)
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(movie)
            }
        }
        fun formatDateString(dateString: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
            val date = inputFormat.parse(dateString)
            return if (date != null) {
                outputFormat.format(date)
            } else {
                ""
            }
        }
    }
}
