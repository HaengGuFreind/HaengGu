package com.example.haenggu.data.remote.services

import com.example.haenggu.data.remote.datasources.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiRequest {

    @GET("/user")
    suspend fun requestHtoken(): Response<String>

    //먼저 수정
    @FormUrlEncoded
    @POST("하위주소")
    fun updateUseript(
        @Header("htoken") htoken: String,
        @Body loginUserIpt: LoginUserIpt
    ): Call<JSONObject>

    @GET("profile")
    suspend fun getProfile() : Response<Profile>

}
