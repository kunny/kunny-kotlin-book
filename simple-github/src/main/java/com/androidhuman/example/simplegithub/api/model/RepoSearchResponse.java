package com.androidhuman.example.simplegithub.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoSearchResponse {

    @SerializedName("total_count")
    public final int totalCount;

    public final List<GithubRepo> items;

    public RepoSearchResponse(int totalCount, List<GithubRepo> items) {
        this.totalCount = totalCount;
        this.items = items;
    }
}
