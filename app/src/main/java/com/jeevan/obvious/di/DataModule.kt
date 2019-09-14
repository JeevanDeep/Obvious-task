package com.jeevan.obvious.di

import android.content.Context
import androidx.room.Room
import com.jeevan.obvious.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "db").build()
    }

    @Provides
    @Singleton
    fun providesPotdDao(appDatabase: AppDatabase) = appDatabase.getPotdDao()
}