package com.example.haenggu.data.remote.datasources

import com.google.gson.annotations.SerializedName

data class LoginUserIpt(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("school")val school: String,
    @SerializedName("major")val major: String,
    @SerializedName("grade")val grade: String,
    @SerializedName("mbti")val mbti: String,
    @SerializedName("event1")val event1: String,
    @SerializedName("event2")val event2: String,
    @SerializedName("event3")val event3: String,
    @SerializedName("region1")val region1: String,
    @SerializedName("region2")val region2: String,
    @SerializedName("region3")val region3: String
)
