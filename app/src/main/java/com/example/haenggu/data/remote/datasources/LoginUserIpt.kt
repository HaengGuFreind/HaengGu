package com.example.haenggu.data.remote.datasources


import com.google.gson.annotations.SerializedName

data class LoginUserIpt(
    @SerializedName("birth")
    val birth: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("grade")
    val grade: String,
    @SerializedName("likes_events")
    val likesEvents: List<String>,
    @SerializedName("likes_regions")
    val likesRegions: List<String>,
    @SerializedName("major")
    val major: String,
    @SerializedName("mbti")
    val mbti: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("school")
    val school: String
)