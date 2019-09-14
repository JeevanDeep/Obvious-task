package com.jeevan.obvious

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.jeevan.obvious.di.ApplicationComponent
import com.jeevan.obvious.di.ApplicationModule
import com.jeevan.obvious.di.DaggerApplicationComponent

class ObviousApp: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        setupDagger()
        Stetho.initializeWithDefaults(this)
        AndroidThreeTen.init(this)
    }

    private fun setupDagger() {
        applicationComponent =
            DaggerApplicationComponent.builder().applicationModule(
                ApplicationModule(
                    this
                )
            ).build()
        applicationComponent.inject(this)
    }

    companion object {
        lateinit var instance: ObviousApp
            private set
        lateinit var applicationComponent: ApplicationComponent
            private set
    }
}