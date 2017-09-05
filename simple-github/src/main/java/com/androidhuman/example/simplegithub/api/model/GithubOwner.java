package com.androidhuman.example.simplegithub.api.model;

import com.google.gson.annotations.SerializedName;

public final class GithubOwner {

    public final String login;

    @SerializedName("avatar_url")
    public final String avatarUrl;

    public GithubOwner(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }
}
