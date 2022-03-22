package com.example.haenggu.data.remote.datasources


import com.google.gson.annotations.SerializedName

data class SchoolItem(
    @SerializedName("dept_id")
    val deptId: String,
    @SerializedName("dept_name")
    val deptName: String
)