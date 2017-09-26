package com.androidhuman.example.simplegithub.extensions

import com.androidhuman.example.simplegithub.rx.LifecycleDisposable
import io.reactivex.disposables.Disposable

operator fun LifecycleDisposable.plusAssign(disposable: Disposable) {
    this.add(disposable)
}