package com.androidhuman.example.simplegithub.di.ui

import android.arch.lifecycle.ViewModelProviders
import com.androidhuman.example.simplegithub.api.GithubApi
import com.androidhuman.example.simplegithub.ui.repo.RepositoryActivity
import com.androidhuman.example.simplegithub.ui.repo.RepositoryViewModel
import com.androidhuman.example.simplegithub.ui.repo.RepositoryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideViewModel(
            activity: RepositoryActivity, factory: RepositoryViewModelFactory): RepositoryViewModel
            = ViewModelProviders.of(activity, factory)[RepositoryViewModel::class.java]

    @Provides
    fun provideViewModelFactory(githubApi: GithubApi): RepositoryViewModelFactory
            = RepositoryViewModelFactory(githubApi)
}
