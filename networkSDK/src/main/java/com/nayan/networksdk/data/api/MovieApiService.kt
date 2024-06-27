package com.nayan.networksdk.data.api

import com.nayan.networksdk.data.model.MovieCastCrewResponse
import com.nayan.networksdk.data.model.MovieDetailsResponse
import com.nayan.networksdk.data.model.MovieImagesResponse
import com.nayan.networksdk.data.model.MovieKeywordsResponse
import com.nayan.networksdk.data.model.MovieRecommendationsResponse
import com.nayan.networksdk.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailsResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieCast(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieCastCrewResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun fetchMovieMediaVideos(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("movie/{movie_id}/images")
    suspend fun fetchMovieImages(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieImagesResponse>

    @GET("movie/{movie_id}/recommendations")
    suspend fun fetchMovieRecommendations(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String
    ): MovieRecommendationsResponse

    @GET("movie/{movie_id}/keywords")
    suspend fun fetchMovieKeywords(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String
    ): MovieKeywordsResponse
}
