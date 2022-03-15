package com.example.haenggu.data.remote.services

import com.example.haenggu.data.remote.util.UrlConstants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiRequest by lazy {
        retrofit.create(ApiRequest::class.java)
    }
}
