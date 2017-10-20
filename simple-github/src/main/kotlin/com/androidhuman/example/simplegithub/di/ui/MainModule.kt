package com.androidhuman.example.simplegithub.di.ui

import android.arch.lifecycle.ViewModelProviders
import com.androidhuman.example.simplegithub.data.SearchHistoryDao
import com.androidhuman.example.simplegithub.ui.main.MainActivity
import com.androidhuman.example.simplegithub.ui.main.MainViewModel
import com.androidhuman.example.simplegithub.ui.main.MainViewModelFactory
import com.androidhuman.example.simplegithub.ui.search.SearchAdapter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideAdapter(activity: MainActivity): SearchAdapter
            = SearchAdapter().apply { setItemClickListener(activity) }

    @Provides
    fun provideViewModel(
            activity: MainActivity, factory: MainViewModelFactory): MainViewModel
            = ViewModelProviders.of(activity, factory)[MainViewModel::class.java]

    @Provides
    fun provideViewModelFactory(searchHistoryDao: SearchHistoryDao): MainViewModelFactory
            = MainViewModelFactory(searchHistoryDao)
}
