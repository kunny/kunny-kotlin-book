package com.androidhuman.example.simplegithub.extensions

import com.androidhuman.example.simplegithub.rx.LifecycleDisposable
import com.androidhuman.example.simplegithub.rx.ViewLifecycleDisposable
import io.reactivex.disposables.Disposable

operator fun LifecycleDisposable.plusAssign(disposable: Disposable)
        = this.add(disposable)

operator fun ViewLifecycleDisposable.plusAssign(disposable: Disposable)
        = this.add(disposable)
