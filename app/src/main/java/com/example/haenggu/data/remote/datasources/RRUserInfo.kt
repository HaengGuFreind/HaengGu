package com.example.haenggu.data.remote.datasources


import com.google.gson.annotations.SerializedName

data class RRUserInfo(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
)