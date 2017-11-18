package com.androidhuman.example.simplegithub.ui.repo

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.api.provideGithubApi
import com.androidhuman.example.simplegithub.extensions.plusAssign
import com.androidhuman.example.simplegithub.rx.AutoClearedDisposable
import com.androidhuman.example.simplegithub.ui.GlideApp
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_repository.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class RepositoryActivity : AppCompatActivity() {

    companion object {

        const val KEY_USER_LOGIN = "user_login"

        const val KEY_REPO_NAME = "repo_name"
    }

    internal val disposables = AutoClearedDisposable(this)

    internal val viewDisposables
            = AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false)

    internal val viewModelFactory by lazy {
        RepositoryViewModelFactory(provideGithubApi(this))
    }

    lateinit var viewModel: RepositoryViewModel

    internal val dateFormatInResponse = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())

    internal val dateFormatToShow = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        viewModel = ViewModelProviders.of(
                this, viewModelFactory)[RepositoryViewModel::class.java]

        lifecycle += disposables
        lifecycle += viewDisposables

        viewDisposables += viewModel.repository
                .filter { !it.isEmpty }
                .map { it.value }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repository ->
                    GlideApp.with(this@RepositoryActivity)
                            .load(repository.owner.avatarUrl)
                            .into(ivActivityRepositoryProfile)

                    tvActivityRepositoryName.text = repository.fullName
                    tvActivityRepositoryStars.text = resources
                            .getQuantityString(R.plurals.star, repository.stars, repository.stars)
                    if (null == repository.description) {
                        tvActivityRepositoryDescription.setText(R.string.no_description_provided)
                    } else {
                        tvActivityRepositoryDescription.text = repository.description
                    }
                    if (null == repository.language) {
                        tvActivityRepositoryLanguage.setText(R.string.no_language_specified)
                    } else {
                        tvActivityRepositoryLanguage.text = repository.language
                    }

                    try {
                        val lastUpdate = dateFormatInResponse.parse(repository.updatedAt)
                        tvActivityRepositoryLastUpdate.text = dateFormatToShow.format(lastUpdate)
                    } catch (e: ParseException) {
                        tvActivityRepositoryLastUpdate.text = getString(R.string.unknown)
                    }
                }

        viewDisposables += viewModel.message
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { message -> showError(message) }

        viewDisposables += viewModel.isContentVisible
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { visible -> setContentVisibility(visible) }

        viewDisposables += viewModel.isLoading
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isLoading ->
                    if (isLoading) {
                        showProgress()
                    } else {
                        hideProgress()
                    }
                }

        val login = intent.getStringExtra(KEY_USER_LOGIN) ?: throw IllegalArgumentException(
                "No login info exists in extras")
        val repo = intent.getStringExtra(KEY_REPO_NAME) ?: throw IllegalArgumentException(
                "No repo info exists in extras")

        disposables += viewModel.requestRepositoryInfo(login, repo)
    }

    private fun showProgress() {
        pbActivityRepository.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        pbActivityRepository.visibility = View.GONE
    }

    private fun setContentVisibility(show: Boolean) {
        llActivityRepositoryContent.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        with(tvActivityRepositoryMessage) {
            text = message
            visibility = View.VISIBLE
        }
    }
}
