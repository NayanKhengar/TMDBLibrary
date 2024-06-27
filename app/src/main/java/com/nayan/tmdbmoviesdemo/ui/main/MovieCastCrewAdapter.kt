package com.nayan.tmdbmoviesdemo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nayan.tmdbmoviesdemo.R
import com.nayan.networksdk.data.model.CastCrew
import com.nayan.tmdbmoviesdemo.databinding.ItemMovieCastcrewBinding
import com.squareup.picasso.Picasso

class MovieCastCrewAdapter(val isCrew: Boolean) :
    RecyclerView.Adapter<MovieCastCrewAdapter.MovieViewHolder>() {

    private var movies: List<CastCrew?>? = listOf()
    fun submitList(movies: List<CastCrew?>?) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemMovieCastcrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies?.get(position))
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    inner class MovieViewHolder(private val binding: ItemMovieCastcrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: CastCrew?) {

            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${movie?.profile_path}")
                .placeholder(R.drawable.user_circle_solid)
                .error(R.drawable.user_circle_solid)
                .into(binding.ivProfile);

            binding.title.text = movie?.original_name
            binding.character.text = if (isCrew) {
                movie?.job
            } else {
                movie?.character
            }
        }
    }
}
