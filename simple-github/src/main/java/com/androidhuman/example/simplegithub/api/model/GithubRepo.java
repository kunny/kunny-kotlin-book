package com.androidhuman.example.simplegithub.api.model;

import com.google.gson.annotations.SerializedName;

public final class GithubRepo {

    public final String name;

    @SerializedName("full_name")
    public final String fullName;

    public final GithubOwner owner;

    public final String description;

    public final String language;

    @SerializedName("updated_at")
    public final String updatedAt;

    @SerializedName("stargazers_count")
    public final int stars;

    public GithubRepo(String name, String fullName,
            GithubOwner owner, String description, String language,
            String updatedAt, int stars) {
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
        this.description = description;
        this.language = language;
        this.updatedAt = updatedAt;
        this.stars = stars;
    }
}
