// NetworkSDK.kt
package com.nayan.networksdk

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class NetworkSDK(private val context: Context) {

    private val apiService: ApiService = NetworkClient.getApiService()

    fun fetchData(callback: Callback<YourResponseModel>) {
        if (!ConnectivityUtils.isNetworkConnected(context)) {
            // Handle no network connection
            callback.onFailure(
                Call<YourResponseModel>,
                IOException("No internet connection")
            )
            return
        }

        val call: Call<YourResponseModel> = apiService.fetchData("909594533c98883408adef5d56143539")
        call.enqueue(callback)
    }

    // Add more methods for other API endpoints and functionalities
}
