package com.androidhuman.example.simplegithub.di.ui

import com.androidhuman.example.simplegithub.data.SearchHistoryDao
import com.androidhuman.example.simplegithub.ui.main.MainActivity
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
    fun provideViewModelFactory(searchHistoryDao: SearchHistoryDao): MainViewModelFactory
            = MainViewModelFactory(searchHistoryDao)
}
