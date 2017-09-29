package com.androidhuman.example.simplegithub.rx

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

class DelayedAutoClearedDisposable(private val lifecycleOwner: AppCompatActivity,
        compositeDisposable: CompositeDisposable = CompositeDisposable())
    : AutoClearedDisposable(lifecycleOwner, compositeDisposable) {

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun cleanUp() {
        if (lifecycleOwner.isFinishing) {
            super.cleanUp()
        }
    }
}