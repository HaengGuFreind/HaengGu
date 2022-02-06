package com.example.haenggu.data.remote.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {
    //retorfit 초기화
    companion object{
        private var retrofit: Retrofit?=null
        //URL 상황봐서 필요하면 URL 모아놓은 Constant class 하나 빼두기
        const val BASE_URL = "http://haengGu"

        val client: Retrofit
            get() {
            if (retrofit ==null){
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            }
            return retrofit!!
        }
    }
}