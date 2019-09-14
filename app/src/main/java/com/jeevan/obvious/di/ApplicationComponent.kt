package com.jeevan.obvious.di


import com.jeevan.obvious.MainActivity
import com.jeevan.obvious.ObviousApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class, DataModule::class])
interface ApplicationComponent {
    fun inject(obviousApp: ObviousApp)
    fun inject(obviousApp: MainActivity)
}