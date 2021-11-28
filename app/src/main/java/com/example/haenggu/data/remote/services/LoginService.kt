package com.example.haenggu.data.remote.services

import com.example.haenggu.data.remote.datasources.LoginUserIpt
import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    @FormUrlEncoded
    @POST("하위주소")
    fun updateUseript(
        @Header("htoken") htoken: String,
        @Body loginUserIpt: LoginUserIpt
    )
    // Success or Fail
}