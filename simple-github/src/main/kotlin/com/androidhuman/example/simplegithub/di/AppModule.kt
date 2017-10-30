package com.androidhuman.example.simplegithub.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Named("appContext")
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext
}
