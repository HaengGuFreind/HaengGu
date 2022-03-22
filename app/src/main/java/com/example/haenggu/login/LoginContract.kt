package com.example.haenggu.login

import android.content.Context
import com.example.haenggu.data.remote.datasources.SchoolItem
import com.example.haenggu.data.remote.datasources.UserInfo

interface LoginContract {

    interface LoginView {
        fun moveToMain()
        fun setFrag(int: Int)
        fun toast(string : String)
    }

    interface LoginPresenter {
        fun hasToken(context: Context)// 토큰 유무 확인 및 검증
        fun onLogin(context: Context)// 로그인 하기
        fun updateUserIpt(userInfo: UserInfo, context: Context)
        fun getSchoolInfo(school_name: String,context: Context)
        fun getMajorList():Pair<Boolean,ArrayList<SchoolItem>>
    }

    interface LoginInterator {
        fun resultKLogin(string: String,context:Context) //로그인 결과 처리
        fun resultHLogin(net:Boolean, role:String) //로그인 결과 처리
        fun resultSchool(net:Boolean, school_item: ArrayList<SchoolItem>)
        fun resultSign(net:Boolean)

    }
    interface KakaoAPIModel {
        // 로그인하기
        fun getLogin(context: Context)
        // 토큰 받기
        fun getToken(context: Context)
    }

    interface LoginRepo {
        fun getHToken(ktoken:String,context: Context) // 서버에 토큰 요청 후 저장
        fun updateUserInfo(userInfo: UserInfo, context: Context)
        fun getSchool(school_name: String, context: Context)
    }

}