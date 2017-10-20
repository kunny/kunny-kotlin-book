package com.androidhuman.example.simplegithub.di.ui

import android.arch.lifecycle.ViewModelProviders
import com.androidhuman.example.simplegithub.api.AuthApi
import com.androidhuman.example.simplegithub.data.AuthTokenProvider
import com.androidhuman.example.simplegithub.ui.signin.SignInActivity
import com.androidhuman.example.simplegithub.ui.signin.SignInViewModel
import com.androidhuman.example.simplegithub.ui.signin.SignInViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SignInModule {

    @Provides
    fun provideViewModel(
            activity: SignInActivity, factory: SignInViewModelFactory): SignInViewModel
            = ViewModelProviders.of(activity, factory)[SignInViewModel::class.java]

    @Provides
    fun provideViewModelFactory(authApi: AuthApi, authTokenProvider: AuthTokenProvider)
            : SignInViewModelFactory
            = SignInViewModelFactory(authApi, authTokenProvider)
}
