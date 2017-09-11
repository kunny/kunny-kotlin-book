package com.androidhuman.example.simplegithub.ui.repo;

import com.androidhuman.example.simplegithub.R;
import com.androidhuman.example.simplegithub.api.GithubApi;
import com.androidhuman.example.simplegithub.api.GithubApiProvider;
import com.androidhuman.example.simplegithub.api.model.GithubRepo;
import com.androidhuman.example.simplegithub.ui.GlideApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryActivity extends AppCompatActivity {

    public static final String KEY_USER_LOGIN = "user_login";

    public static final String KEY_REPO_NAME = "repo_name";

    LinearLayout llContent;

    ImageView ivProfile;

    TextView tvName;

    TextView tvStars;

    TextView tvDescription;

    TextView tvLanguage;

    TextView tvLastUpdate;

    ProgressBar pbProgress;

    TextView tvMessage;

    GithubApi api;

    Call<GithubRepo> repoCall;

    SimpleDateFormat dateFormatInResponse = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault());

    SimpleDateFormat dateFormatToShow = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        llContent = findViewById(R.id.llActivityRepositoryContent);
        ivProfile = findViewById(R.id.ivActivityRepositoryProfile);
        tvName = findViewById(R.id.tvActivityRepositoryName);
        tvStars = findViewById(R.id.tvActivityRepositoryStars);
        tvDescription = findViewById(R.id.tvActivityRepositoryDescription);
        tvLanguage = findViewById(R.id.tvActivityRepositoryLanguage);
        tvLastUpdate = findViewById(R.id.tvActivityRepositoryLastUpdate);
        pbProgress = findViewById(R.id.pbActivityRepository);
        tvMessage = findViewById(R.id.tvActivityRepositoryMessage);

        api = GithubApiProvider.provideGithubApi(this);

        String login = getIntent().getStringExtra(KEY_USER_LOGIN);
        if (null == login) {
            throw new IllegalArgumentException("No login info exists in extras");
        }
        String repo = getIntent().getStringExtra(KEY_REPO_NAME);
        if (null == repo) {
            throw new IllegalArgumentException("No repo info exists in extras");
        }

        showRepositoryInfo(login, repo);
    }

    private void showRepositoryInfo(String login, String repoName) {
        showProgress();

        repoCall = api.getRepository(login, repoName);
        repoCall.enqueue(new Callback<GithubRepo>() {
            @Override
            public void onResponse(Call<GithubRepo> call, Response<GithubRepo> response) {
                hideProgress(true);

                GithubRepo repo = response.body();
                if (response.isSuccessful() && null != repo) {
                    GlideApp.with(RepositoryActivity.this)
                            .load(repo.owner.avatarUrl)
                            .into(ivProfile);

                    tvName.setText(repo.fullName);
                    tvStars.setText(getResources()
                            .getQuantityString(R.plurals.star, repo.stars, repo.stars));
                    if (null == repo.description) {
                        tvDescription.setText(R.string.no_description_provided);
                    } else {
                        tvDescription.setText(repo.description);
                    }
                    if (null == repo.language) {
                        tvLanguage.setText(R.string.no_language_specified);
                    } else {
                        tvLanguage.setText(repo.language);
                    }

                    try {
                        Date lastUpdate = dateFormatInResponse.parse(repo.updatedAt);
                        tvLastUpdate.setText(dateFormatToShow.format(lastUpdate));
                    } catch (ParseException e) {
                        tvLastUpdate.setText(getString(R.string.unknown));
                    }
                } else {
                    showError("Not successful: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GithubRepo> call, Throwable t) {
                hideProgress(false);
                showError(t.getMessage());
            }
        });
    }

    private void showProgress() {
        llContent.setVisibility(View.GONE);
        pbProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgress(boolean isSucceed) {
        llContent.setVisibility(isSucceed ? View.VISIBLE : View.GONE);
        pbProgress.setVisibility(View.GONE);
    }

    private void showError(String message) {
        tvMessage.setText(message);
        tvMessage.setVisibility(View.VISIBLE);
    }
}
