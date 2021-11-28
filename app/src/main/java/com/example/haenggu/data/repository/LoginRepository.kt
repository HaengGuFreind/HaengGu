package com.example.haenggu.data.repository

import android.app.Activity
import android.content.Context
import com.example.haenggu.data.local.SharedManager
import com.example.haenggu.data.remote.datasources.LoginUserIpt
import com.example.haenggu.data.remote.services.LoginService
import com.example.haenggu.data.remote.services.ServerAPI
import com.example.haenggu.login.LoginContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository: LoginContract.LoginModel {
    private var apiclient: LoginService?=null

    init{
        apiclient = ServerAPI.client.create(LoginService::class.java)
    }

    override fun getHToken(ktoken: String): String {
        TODO("Not yet implemented")
    }

    override fun updateUserInfo(loginuseript:LoginUserIpt,context: Context) {
        //    private val sharedManager : SharedManager by lazy {SharedManager(context)}
//        val call = apiclient?.updateUseript("htoken", loginuseript)
//        call?.enqueue(
//            object : Callback<String> {
//                override fun onFailure(call: Call<String>, t: Throwable) {
//
//                }
//
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    if(response.isSuccessful){
//                        //성공하면
//            sharedManager.saveHToken(response.get())
//
//                    }
//                }
//            }
//        )
    }
}
