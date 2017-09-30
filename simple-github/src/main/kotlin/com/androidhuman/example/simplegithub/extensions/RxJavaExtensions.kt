package com.androidhuman.example.simplegithub.extensions

import com.androidhuman.example.simplegithub.rx.AutoClearedDisposable
import com.androidhuman.example.simplegithub.rx.DelayedAutoClearedDisposable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

operator fun AutoClearedDisposable.plusAssign(disposable: Disposable)
        = this.add(disposable)

operator fun DelayedAutoClearedDisposable.plusAssign(disposable: Disposable)
        = this.add(disposable)

fun <T> runOnIoScheduler(func: () -> T): Disposable
        = Single.fromCallable(func).subscribeOn(Schedulers.io()).subscribe()