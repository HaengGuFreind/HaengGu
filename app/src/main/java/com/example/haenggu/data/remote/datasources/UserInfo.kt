package com.example.haenggu.data.remote.datasources


import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH

data class UserInfo(
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("category_tag")
    val categoryTag: List<String>,
    @SerializedName("dept_id")
    val deptId: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("mbti")
    val mbti: String,
    @SerializedName("region_tag")
    val regionTag: List<String>,
    @SerializedName("username")
    val username: String
)

