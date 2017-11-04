package com.androidhuman.example.simplegithub.di.ui

import com.androidhuman.example.simplegithub.api.GithubApi
import com.androidhuman.example.simplegithub.ui.repo.RepositoryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideViewModelFactory(githubApi: GithubApi): RepositoryViewModelFactory
            = RepositoryViewModelFactory(githubApi)
}
