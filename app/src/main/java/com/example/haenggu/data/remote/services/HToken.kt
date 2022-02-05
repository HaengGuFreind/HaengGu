package com.example.haenggu.data.remote.services

import com.example.haenggu.data.remote.datasources.LoginUserIpt
import com.kakao.sdk.user.model.User
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface HToken {
    @GET("/user")
    fun requestHtoken(
        @Query("token") token:String ): Call<JSONObject>
}