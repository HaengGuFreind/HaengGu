package com.example.haenggu.data.remote.services

import com.example.haenggu.data.remote.datasources.LoginUserIpt
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface UserIpt {
    @FormUrlEncoded
    @POST("하위주소")
    fun updateUseript(
        @Header("htoken") htoken: String,
        @Body loginUserIpt: LoginUserIpt
    ): Call<JSONObject>
}