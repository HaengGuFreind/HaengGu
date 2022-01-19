package com.example.haenggu.login

import android.content.Context
import com.example.haenggu.data.remote.datasources.LoginUserIpt

interface LoginContract {

    interface LoginView {
        fun moveToMain()
        fun setFrag(int: Int)
        fun toast(string : String)
    }

    interface LoginPresenter {
        fun isLogin(context: Context):String // 토큰 유무 확인 및 검증
        fun onLogin(context: Context) // 로그인 후 결과 처리
        fun updateUserIpt(loginUserIpt: LoginUserIpt, context: Context)
    }

    interface KakaoAPIModel {
        // 로그인하기
        fun getLogin(context: Context, hasToken:String): Pair<String,String>
        // 토큰 받기
        fun hasToken():String
    }

    interface LoginRepo {
        fun getHToken(ktoken:String,context: Context):String // 서버에 토큰 요청 후 저장
        fun updateUserInfo(loginUserIpt: LoginUserIpt,context: Context)
    }

}