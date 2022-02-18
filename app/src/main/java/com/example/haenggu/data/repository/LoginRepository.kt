package com.example.haenggu.data.repository

import android.content.Context
import com.example.haenggu.data.local.SharedManager
import com.example.haenggu.data.remote.datasources.LoginUserIpt
import com.example.haenggu.login.LoginContract
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository : LoginContract.LoginRepo {

    override fun getHToken(ktoken: String, context: Context): String {
        return "Success"
    }


    override fun updateUserInfo(loginuseript: LoginUserIpt, context: Context) {
      
    }
}
