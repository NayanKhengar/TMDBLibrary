package com.nayan.tmdbmoviesdemo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.nayan.tmdbmoviesdemo.databinding.ActivityMovieDetailBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: MovieViewModel
    private var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        val movieId = intent.getIntExtra("MOVIE_ID", -1)
        adapter = MovieAdapter()
        binding.rvFullCast.layoutManager =
            LinearLayoutManager(this@MovieDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvFullCrew.layoutManager =
            LinearLayoutManager(this@MovieDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMedia.layoutManager =
            LinearLayoutManager(this@MovieDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommendations.layoutManager =
            LinearLayoutManager(this@MovieDetailActivity, LinearLayoutManager.HORIZONTAL, false)

        val movieImagesAdapter = MovieImagesAdapter()
        binding.rvMedia.adapter = movieImagesAdapter

        val movieRecommendationsAdapter = MovieRecommendationsAdapter()
        binding.rvRecommendations.adapter = movieRecommendationsAdapter

        val movieCrewAdapter = MovieCastCrewAdapter(true)
        binding.rvFullCrew.adapter = movieCrewAdapter
        val movieCastAdapter = MovieCastCrewAdapter(false)
        binding.rvFullCast.adapter = movieCastAdapter

        viewModel.movieDetails.observe(this) { movie ->
            binding.apply {
                tvTitle.text = movie?.title
                tvYear.text = "(" + formatDateString(movie.release_date) + ")"
                tvOverview.text = movie?.overview
                Picasso.get().load("https://image.tmdb.org/t/p/w500${movie?.backdrop_path}")
                    .into(ivBanner)
                Picasso.get().load("https://image.tmdb.org/t/p/w500${movie?.poster_path}")
                    .into(ivPoster)
                pbPopular.progress = (movie?.vote_average?.times(10))?.toInt() ?: 0
                tvScore.text = (movie?.vote_average?.times(10))?.toInt().toString() + "%"
                if (movie.adult) {
                    tvAdult.text = "A"
                } else {
                    tvAdult.text = "U"
                }
                tvReleaseDate.text = movie.release_date
                val hms = "• " + String.format(
                    "%02dh %02dm", TimeUnit.MINUTES.toHours(movie.runtime),
                    TimeUnit.MINUTES.toMinutes(
                        movie.runtime
                    ) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MINUTES.toHours(
                            movie.runtime
                        )
                    )
                )
                tvDuration.text = hms
                var type = ""
                for (genre in movie.genres) {
                    if (type.isEmpty()) {
                        type = genre.name
                    } else {
                        type = type + ", " + genre.name
                    }
                }
                tvType.text = type
                tvTagLine.text = movie.tagline
                tvStatus.text = movie.status
                var lang = ""
                for (language in movie.spoken_languages) {
                    if (lang.isEmpty()) {
                        lang = language.name
                    } else {
                        lang = lang + ", " + language.name
                    }
                }
                tvLang.text = lang
                tvBudget.text = "$" + movie.budget.toString()
                tvRevenue.text = "$" + movie.revenue.toString()

            }

        }
        viewModel.movieCast.observe(this) { movies ->
            movieCrewAdapter.submitList(movies?.crew)
            movieCastAdapter.submitList(movies?.cast)
        }

        viewModel.movieImages.observe(this) { movies ->
            movieImagesAdapter.submitList(movies.backdrops)
        }
        viewModel.movieRecommendations.observe(this) { movies ->
            movieRecommendationsAdapter.submitList(movies)
        }
        viewModel.movieKeywords.observe(this) { movies ->

            for (keyword in movies) {
                val chip = Chip(this)
                chip.text = keyword.name
                chip.isCloseIconVisible = false
                binding.chipKeywords.addView(chip)
            }

        }

        viewModel.fetchMovieDetails(movieId)
        viewModel.fetchMovieCast(movieId)
//        viewModel.fetchMovieMediaVideos(movieId)
        viewModel.fetchMovieImages(movieId)
        viewModel.fetchMovieRecommendations(movieId)
        viewModel.fetchMovieKeywords(movieId)

    }

    fun formatDateString(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return if (date != null) {
            outputFormat.format(date)
        } else {
            ""
        }
    }
}
