package com.nayan.tmdbmoviesdemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nayan.tmdbmoviesdemo.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val movie = arguments?.getParcelable<Movie>("movie")
//        movie?.let {
//            binding.title.text = it.title
//            binding.overview.text = it.overview
//            Picasso.get().load("https://image.tmdb.org/t/p/w500${it.poster_path}").into(binding.poster)
//        }
    }
}
