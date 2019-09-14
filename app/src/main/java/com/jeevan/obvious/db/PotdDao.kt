package com.jeevan.obvious.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jeevan.obvious.home.response.PictureOfTheDayResponse

@Dao
interface PotdDao {

    @Insert
    suspend fun insertData(pictureOfTheDayResponse: PictureOfTheDayResponse)

    @Query("SELECT * FROM potd_table WHERE date = :date")
    suspend fun getPotd(date: String): PictureOfTheDayResponse?
}