package com.androidhuman.example.simplegithub.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.data.SearchHistoryDao

class MainViewModelFactory(val searchHistoryDao: SearchHistoryDao)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(searchHistoryDao) as T
    }
}
