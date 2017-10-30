package com.androidhuman.example.simplegithub

import com.androidhuman.example.simplegithub.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class SimpleGithubApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
