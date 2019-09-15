package com.jeevan.obvious.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeevan.obvious.home.response.PictureOfTheDayResponse
import com.jeevan.obvious.network.NetworkResult
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {
    private var currentDate: LocalDate = LocalDate.now()

    private var _canRequestMore = false
    val canRequestMore: Boolean
        get() = _canRequestMore

    private val _potdResponse = MutableLiveData<List<PictureOfTheDayResponse>>()
    val potdResponse: LiveData<List<PictureOfTheDayResponse>>
        get() = _potdResponse

    fun getPotd() {
        _canRequestMore = false
        viewModelScope.launch {
            val list = mutableListOf<PictureOfTheDayResponse>()
            for (i in 0 until 10) {
                val newDate = currentDate.minusDays(i.toLong())
                val response = homeRepo.getPotd(newDate.toString())
                if (response is NetworkResult.Success) {
                    list.add(response.data)
                }
            }
            _potdResponse.value = list
            currentDate = currentDate.minusDays(10)
            _canRequestMore = true
        }
    }
}