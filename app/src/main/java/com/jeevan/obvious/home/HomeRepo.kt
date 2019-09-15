package com.jeevan.obvious.home

import com.jeevan.obvious.db.PotdDao
import com.jeevan.obvious.home.response.PictureOfTheDayResponse
import com.jeevan.obvious.network.ApiClient
import com.jeevan.obvious.network.NetworkResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepo @Inject constructor(private val apiClient: ApiClient, private val dao: PotdDao) {
    suspend fun getPotd(date: String): NetworkResult<PictureOfTheDayResponse> {
        var networkResult: NetworkResult<PictureOfTheDayResponse>? = null

        runCatching {
            val responseFromDb = dao.getPotd(date)
            if (responseFromDb == null) {
                val response = apiClient.getPotd(date = date)
                dao.insertData(response)
                networkResult = NetworkResult.Success(response)
            } else {
                networkResult = NetworkResult.Success(responseFromDb)
            }
        }.onFailure {
            networkResult = NetworkResult.Error(it)
        }
        return networkResult!!
    }
}