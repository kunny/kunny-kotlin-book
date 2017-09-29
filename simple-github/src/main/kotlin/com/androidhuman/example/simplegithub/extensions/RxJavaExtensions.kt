package com.androidhuman.example.simplegithub.extensions

import com.androidhuman.example.simplegithub.rx.AutoClearedDisposable
import com.androidhuman.example.simplegithub.rx.DelayedAutoClearedDisposable
import io.reactivex.disposables.Disposable

operator fun AutoClearedDisposable.plusAssign(disposable: Disposable)
        = this.add(disposable)

operator fun DelayedAutoClearedDisposable.plusAssign(disposable: Disposable)
        = this.add(disposable)
