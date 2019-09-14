package com.jeevan.obvious

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jeevan.obvious.di.ViewModelFactory
import com.jeevan.obvious.home.HomeViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val homeViewmodel: HomeViewModel by viewModels { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ObviousApp.applicationComponent.inject(this)

        homeViewmodel.getPotd()
    }
}
