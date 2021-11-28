package com.example.haenggu.login

import android.content.Context
import com.example.haenggu.data.remote.datasources.LoginUserIpt
import com.google.gson.JsonObject

interface LoginContract {

    interface LoginView {
        fun moveToMain()
        fun setFrag(int: Int)
        fun toast(string : String)
    }

    interface LoginPresenter {
        fun isLogin():String
        fun onLogin(context: Context)
        fun updateUserIpt(loginUserIpt: LoginUserIpt,context: Context)
    }

    interface KakaoAPIModel {
        fun getLogin(context: Context, hasToken:String): Pair<String,String>
        fun hasToken():String
    }

    interface LoginModel {
        fun getHToken(ktoken:String):String
        fun updateUserInfo(loginUserIpt: LoginUserIpt,context: Context)
    }


}