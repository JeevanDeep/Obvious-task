package com.jeevan.obvious.network

import com.jeevan.obvious.BuildConfig
import com.jeevan.obvious.home.response.PictureOfTheDayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET(NetworkConstants.GET_POTD)
    suspend fun getPotd(@Query("date") date: String, @Query("api_key") apiKey: String = BuildConfig.API_KEY): PictureOfTheDayResponse
}