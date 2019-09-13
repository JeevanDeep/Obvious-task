package com.jeevan.obvious.di

import android.app.Application
import android.content.Context
import com.jeevan.obvious.ObviousApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(var obviousApp: ObviousApp) {

    @Provides
    @Singleton
    fun provideApp(): Application {
        return obviousApp
    }

    @Provides
    @Singleton
    fun getContext(): Context = obviousApp

}