// ApiService.kt
package com.nayan.networksdk

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{movie_id}/keywords")
    suspend fun fetchMovieKeywords(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String
    ): Call<YourResponseModel>

    @GET("movie/278/keywords")
    fun fetchData(@Query("api_key") apiKey: String): Call<YourResponseModel>
}
