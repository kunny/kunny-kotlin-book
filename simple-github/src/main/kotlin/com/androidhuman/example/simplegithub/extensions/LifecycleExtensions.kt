package com.androidhuman.example.simplegithub.extensions

import android.arch.lifecycle.Lifecycle
import com.androidhuman.example.simplegithub.rx.LifecycleDisposable

operator fun Lifecycle.plusAssign(observer: LifecycleDisposable)
        = this.addObserver(observer)
