package com.androidhuman.example.simplegithub.api.model

import com.google.gson.annotations.SerializedName

class GithubRepo(
        val name: String,
        @SerializedName("full_name") val fullName: String,
        val owner: GithubOwner,
        val description: String?,
        val language: String?,
        @SerializedName("updated_at") val updatedAt: String,
        @SerializedName("stargazers_count") val stars: Int)
