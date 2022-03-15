package com.example.haenggu.data.remote.datasources


import com.google.gson.annotations.SerializedName

data class RRUserInfo(
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("dept_id")
    val deptId: Any,
    @SerializedName("email")
    val email: String,
    @SerializedName("event_tag")
    val eventTag: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("modified_date")
    val modifiedDate: String,
    @SerializedName("principal")
    val principal: String,
    @SerializedName("region_tag")
    val regionTag: List<String>,
    @SerializedName("role_type")
    val roleType: String,
    @SerializedName("social_type")
    val socialType: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("username")
    val username: String
)