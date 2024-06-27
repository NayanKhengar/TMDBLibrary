package com.nayan.tmdbmoviesdemo.data.repository

import com.nayan.tmdbmoviesdemo.data.api.MovieApiService
import com.nayan.tmdbmoviesdemo.data.api.RetrofitClient
import com.nayan.tmdbmoviesdemo.data.model.MovieCastCrewResponse
import com.nayan.tmdbmoviesdemo.data.model.MovieDetailsResponse
import com.nayan.tmdbmoviesdemo.data.model.MovieImagesResponse
import com.nayan.tmdbmoviesdemo.data.model.MovieKeywordsResponse
import com.nayan.tmdbmoviesdemo.data.model.MovieRecommendationsResponse
import com.nayan.tmdbmoviesdemo.data.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepository {

    private val apiService = RetrofitClient.instance.create(MovieApiService::class.java)
    private val apiKey = "909594533c98883408adef5d56143539"

    suspend fun getTopRatedMovies(): MovieResponse = withContext(Dispatchers.IO) {
        apiService.getTopRatedMovies(apiKey)
    }

    suspend fun getPopularMovies(): MovieResponse = withContext(Dispatchers.IO) {
        apiService.getPopularMovies(apiKey)
    }

    suspend fun fetchMovieDetails(movieID: Int): Response<MovieDetailsResponse> = withContext(Dispatchers.IO) {
        apiService.fetchMovieDetails(movieID,apiKey)
    }

    suspend fun fetchMovieCast(movieID: Int): Response<MovieCastCrewResponse> = withContext(Dispatchers.IO) {
        apiService.fetchMovieCast(movieID,apiKey)
    }

    suspend fun fetchMovieMediaVideos(movieID: Int): MovieResponse = withContext(Dispatchers.IO) {
        apiService.fetchMovieMediaVideos(movieID,apiKey)
    }

    suspend fun fetchMovieImages(movieID: Int): Response<MovieImagesResponse> = withContext(Dispatchers.IO) {
        apiService.fetchMovieImages(movieID,apiKey)
    }

    suspend fun fetchMovieRecommendations(movieID: Int): MovieRecommendationsResponse = withContext(Dispatchers.IO) {
        apiService.fetchMovieRecommendations(movieID,apiKey)
    }

    suspend fun fetchMovieKeywords(movieID: Int): MovieKeywordsResponse = withContext(Dispatchers.IO) {
        apiService.fetchMovieKeywords(movieID,apiKey)
    }
}
