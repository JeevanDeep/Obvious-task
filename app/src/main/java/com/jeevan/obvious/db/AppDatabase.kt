package com.jeevan.obvious.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeevan.obvious.home.response.PictureOfTheDayResponse

@Database(entities = [PictureOfTheDayResponse::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPotdDao(): PotdDao
}