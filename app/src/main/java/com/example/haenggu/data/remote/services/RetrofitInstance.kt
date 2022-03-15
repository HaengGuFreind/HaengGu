package com.example.haenggu.data.remote.services

import com.example.haenggu.data.remote.util.UrlConstants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    var interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    var client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

//    var gson = GsonBuilder()
//        .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
//        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api: ApiRequest by lazy {
        retrofit.create(ApiRequest::class.java)
    }
}
