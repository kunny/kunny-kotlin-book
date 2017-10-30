package com.androidhuman.example.simplegithub.ui.search

import android.arch.lifecycle.ViewModel
import com.androidhuman.example.simplegithub.api.GithubApi
import com.androidhuman.example.simplegithub.api.model.GithubRepo
import com.androidhuman.example.simplegithub.data.SearchHistoryDao
import com.androidhuman.example.simplegithub.extensions.runOnIoScheduler
import com.androidhuman.example.simplegithub.util.SupportOptional
import com.androidhuman.example.simplegithub.util.emptyOptional
import com.androidhuman.example.simplegithub.util.optionalOf
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

class SearchViewModel(
        val api: GithubApi,
        val searchHistoryDao: SearchHistoryDao)
    : ViewModel() {

    val searchResult: BehaviorSubject<SupportOptional<List<GithubRepo>>>
            = BehaviorSubject.createDefault(emptyOptional())

    val lastSearchKeyword: BehaviorSubject<SupportOptional<String>>
            = BehaviorSubject.createDefault(emptyOptional())

    val message: BehaviorSubject<SupportOptional<String>> = BehaviorSubject.create()

    val isLoading: BehaviorSubject<Boolean>
            = BehaviorSubject.createDefault(false)

    fun searchRepository(query: String): Disposable
            = api.searchRepository(query)
            .doOnNext { lastSearchKeyword.onNext(optionalOf(query)) }
            .flatMap {
                if (0 == it.totalCount) {
                    Observable.error(IllegalStateException("No search result"))
                } else {
                    Observable.just(it.items)
                }
            }
            .doOnSubscribe {
                searchResult.onNext(emptyOptional())
                message.onNext(emptyOptional())
                isLoading.onNext(true)
            }
            .doOnTerminate { isLoading.onNext(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                searchResult.onNext(optionalOf(items))
            }) {
                message.onNext(optionalOf(it.message ?: "Unexpected error"))
            }

    fun addToSearchHistory(repository: GithubRepo): Disposable
            = runOnIoScheduler { searchHistoryDao.add(repository) }
}
