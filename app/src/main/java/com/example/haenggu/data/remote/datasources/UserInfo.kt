package com.example.haenggu.data.remote.datasources


import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("categoryTag")
    val categoryTag: List<String>,
    @SerializedName("dept_id")
    val deptId: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("schoolName")
    val schoolName: String,
    @SerializedName("regionTag")
    val regionTag: List<String>,
    @SerializedName("username")
    val username: String,
)