package com.nayan.tmdbmoviesdemo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nayan.tmdbmoviesdemo.data.model.CastCrew
import com.nayan.tmdbmoviesdemo.data.model.KeyValue
import com.nayan.tmdbmoviesdemo.data.model.Movie
import com.nayan.tmdbmoviesdemo.data.model.MovieCastCrewResponse
import com.nayan.tmdbmoviesdemo.data.model.MovieDetailsResponse
import com.nayan.tmdbmoviesdemo.data.model.MovieImagesResponse
import com.nayan.tmdbmoviesdemo.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _topratedtMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies: LiveData<List<Movie>> get() = _topratedtMovies

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> get() = _popularMovies

    private val _movieDetails = MutableLiveData<MovieDetailsResponse>()
    val movieDetails: LiveData<MovieDetailsResponse> get() = _movieDetails

    private val _movieCast = MutableLiveData<MovieCastCrewResponse>()
    val movieCast: LiveData<MovieCastCrewResponse> get() = _movieCast

    private val _movieVideos = MutableLiveData<List<Movie>>()
    val movieVideos: LiveData<List<Movie>> get() = _movieVideos

    private val _movieImages = MutableLiveData<MovieImagesResponse>()
    val movieImages: LiveData<MovieImagesResponse> get() = _movieImages

    private val _movieRecommendations = MutableLiveData<List<Movie>>()
    val movieRecommendations: LiveData<List<Movie>> get() = _movieRecommendations

    private val _movieKeywords = MutableLiveData<List<KeyValue>>()
    val movieKeywords: LiveData<List<KeyValue>> get() = _movieKeywords


    fun fetchTopRatedMovies() {
        viewModelScope.launch {
            val response = repository.getTopRatedMovies()
            _topratedtMovies.postValue(response.results)
        }
    }

    fun fetchPopularMovies() {
        viewModelScope.launch {
            val response = repository.getPopularMovies()
            _popularMovies.postValue(response.results)
        }
    }

    fun fetchMovieDetails(movieID: Int) {
        viewModelScope.launch {
            val response = repository.fetchMovieDetails(movieID)
            _movieDetails.postValue(response.body())
        }
    }

    fun fetchMovieCast(movieID: Int) {
        viewModelScope.launch {
            val response = repository.fetchMovieCast(movieID)
            _movieCast.postValue(response.body())
        }
    }

    fun fetchMovieMediaVideos(movieID: Int) {
        viewModelScope.launch {
            val response = repository.fetchMovieMediaVideos(movieID)
            _movieVideos.postValue(response.results)
        }
    }

    fun fetchMovieImages(movieID: Int) {
        viewModelScope.launch {
            val response = repository.fetchMovieImages(movieID)
            _movieImages.postValue(response.body())
        }
    }

    fun fetchMovieRecommendations(movieID: Int) {
        viewModelScope.launch {
            val response = repository.fetchMovieRecommendations(movieID)
            _movieRecommendations.postValue(response.results)
        }
    }

    fun fetchMovieKeywords(movieID: Int) {
        viewModelScope.launch {
            val response = repository.fetchMovieKeywords(movieID)
            _movieKeywords.postValue(response.keywords)
        }
    }
}
