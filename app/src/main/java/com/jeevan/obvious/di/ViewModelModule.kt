package com.jeevan.obvious.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeevan.obvious.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun providesHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}

