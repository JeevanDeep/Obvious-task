package com.jeevan.obvious.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {
    private var currentDate: LocalDate = LocalDate.now()
    fun getPotd() {
        viewModelScope.launch {
            for (i in 0 until 10) {
                val newDate = currentDate.minusDays(i.toLong())

                val response = homeRepo.getPotd(newDate.toString())
            }
            currentDate = currentDate.minusDays(10)
        }
    }
}