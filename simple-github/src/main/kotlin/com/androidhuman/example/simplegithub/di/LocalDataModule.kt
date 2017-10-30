package com.androidhuman.example.simplegithub.di

import android.arch.persistence.room.Room
import android.content.Context
import com.androidhuman.example.simplegithub.data.AuthTokenProvider
import com.androidhuman.example.simplegithub.data.SearchHistoryDao
import com.androidhuman.example.simplegithub.data.SimpleGithubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class LocalDataModule {

    // SharedPreferences

    @Provides
    fun provideAuthTokenProvider(@Named("appContext") context: Context): AuthTokenProvider
            = AuthTokenProvider(context)
    
    // Room Database

    @Provides
    fun provideSearchHistoryDao(db: SimpleGithubDatabase): SearchHistoryDao
            = db.searchHistoryDao()

    @Provides
    @Singleton
    fun provideDatabase(@Named("appContext") context: Context): SimpleGithubDatabase
            = Room.databaseBuilder(context,
            SimpleGithubDatabase::class.java, "simple_github.db")
            .build()
}
