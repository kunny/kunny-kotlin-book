package com.androidhuman.example.simplegithub.data

import android.arch.persistence.room.Room
import android.content.Context

fun provideSearchHistoryDao(context: Context): SearchHistoryDao
        = provideDatabase(context).searchHistoryDao()

private fun provideDatabase(context: Context): SimpleGithubDatabase
        = Room.databaseBuilder(context.applicationContext,
        SimpleGithubDatabase::class.java, "simple_github.db")
        .build()