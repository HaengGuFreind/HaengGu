package com.example.haenggu.data.remote.datasources

import com.google.gson.annotations.SerializedName

data class Login (
    val role_type: String,
    val token: String,
    val refresh_token: String
        )