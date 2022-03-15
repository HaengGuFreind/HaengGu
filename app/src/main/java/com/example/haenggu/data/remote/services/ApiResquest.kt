package com.example.haenggu.data.remote.services

import com.example.haenggu.data.remote.datasources.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiRequest {

    @GET("oauth/login/kakao")
    fun getHToken(
        @Header ("Authorization") htoken:String
    ):Call<Login>

    @PATCH("users")
    fun updateUseript(
        @Header ("Authorization") htoken:String,
        @Body userinfo: UserInfo
    ):Call<RRUserInfo>

    @GET("users/majors")
    fun getSchool(
        @Query ("school-name")school_name: String
    ):Call<School>

    @GET("profile")
    suspend fun getProfile() : Response<Profile>

    @GET("events")
    fun getEventList(@QueryMap options:Map<String, String>): Call<ResponseEvent>

    @GET("events/{idx}")
    fun getEvenDetail(@Path("idx") idx:String): Call<ResponseEventDetail>
}
