package com.androidhuman.example.simplegithub.di

import com.androidhuman.example.simplegithub.di.ui.MainModule
import com.androidhuman.example.simplegithub.di.ui.RepositoryModule
import com.androidhuman.example.simplegithub.di.ui.SearchModule
import com.androidhuman.example.simplegithub.di.ui.SignInModule
import com.androidhuman.example.simplegithub.ui.main.MainActivity
import com.androidhuman.example.simplegithub.ui.repo.RepositoryActivity
import com.androidhuman.example.simplegithub.ui.search.SearchActivity
import com.androidhuman.example.simplegithub.ui.signin.SignInActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinder {

    @ContributesAndroidInjector(modules = arrayOf(SignInModule::class))
    abstract fun bindSignInActivity(): SignInActivity

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(SearchModule::class))
    abstract fun bindSearchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = arrayOf(RepositoryModule::class))
    abstract fun bindRepositoryActivity(): RepositoryActivity
}