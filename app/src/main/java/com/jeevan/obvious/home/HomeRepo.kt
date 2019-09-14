package com.jeevan.obvious.home

import com.jeevan.obvious.home.response.PictureOfTheDayResponse
import com.jeevan.obvious.network.ApiClient
import com.jeevan.obvious.network.NetworkResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepo @Inject constructor(private val apiClient: ApiClient) {
    suspend fun getPotd(date: String): NetworkResult<PictureOfTheDayResponse> {
        var networkResult: NetworkResult<PictureOfTheDayResponse>? = null

        runCatching {
            val response = apiClient.getPotd(date = date)
            networkResult = NetworkResult.Success(response)
            // todo: add db implementation
        }.onFailure {
            networkResult = NetworkResult.Error(it)
        }
        return networkResult!!
    }
}