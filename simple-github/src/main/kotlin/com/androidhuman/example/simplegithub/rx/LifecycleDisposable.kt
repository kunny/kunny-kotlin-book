package com.androidhuman.example.simplegithub.rx

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class LifecycleDisposable(
        private val lifecycleOwner: LifecycleOwner,
        private val compositeDisposable: CompositeDisposable = CompositeDisposable())
    : LifecycleObserver {

    fun add(disposable: Disposable) {
        check(lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED))
        compositeDisposable.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun cleanUp() {
        compositeDisposable.clear()
    }
}