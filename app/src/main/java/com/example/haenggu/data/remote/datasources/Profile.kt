package com.example.haenggu.data.remote.datasources


import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("boards")
    val boards: List<String>,
    @SerializedName("favorite")
    val favorite: List<String>,
    @SerializedName("introduction")
    val introduction: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("username")
    val username: String
)
