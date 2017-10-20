package com.androidhuman.example.simplegithub.di.ui

import android.arch.lifecycle.ViewModelProviders
import com.androidhuman.example.simplegithub.api.GithubApi
import com.androidhuman.example.simplegithub.data.SearchHistoryDao
import com.androidhuman.example.simplegithub.ui.search.SearchActivity
import com.androidhuman.example.simplegithub.ui.search.SearchAdapter
import com.androidhuman.example.simplegithub.ui.search.SearchViewModel
import com.androidhuman.example.simplegithub.ui.search.SearchViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    fun provideAdapter(activity: SearchActivity): SearchAdapter
            = SearchAdapter().apply { setItemClickListener(activity) }

    @Provides
    fun provideViewModel(
            activity: SearchActivity, factory: SearchViewModelFactory): SearchViewModel
            = ViewModelProviders.of(activity, factory)[SearchViewModel::class.java]

    @Provides
    fun provideViewModelFactory(
            githubApi: GithubApi, searchHistoryDao: SearchHistoryDao): SearchViewModelFactory
            = SearchViewModelFactory(githubApi, searchHistoryDao)
}
