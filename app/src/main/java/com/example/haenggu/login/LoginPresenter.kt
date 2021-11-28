package com.example.haenggu.login

import android.content.Context
import android.widget.Toast
import com.example.haenggu.data.api.KaKaoAPI
import com.example.haenggu.data.remote.datasources.LoginUserIpt
import com.example.haenggu.data.repository.LoginRepository
import com.google.gson.JsonObject

class LoginPresenter (view:LoginContract.LoginView):LoginContract.LoginPresenter{
    private var view: LoginContract.LoginView = view
    private var api_model: LoginContract.KakaoAPIModel = KaKaoAPI()
    private var repos_model: LoginContract.LoginModel = LoginRepository()
    private var hasKToken : String ?= null

    override fun isLogin(): String {
        // 이전 로그인 유무 확인
        hasKToken = api_model.hasToken()
        if (hasKToken == "False" || hasKToken == "NoToken"){
            view.setFrag(1)
        }else{
            //
            // shared peference에 저장
            repos_model.getHToken(hasKToken!!)
            view.moveToMain()
        }
        TODO("Not yet implemented")
    }

    override fun onLogin(context: Context) {
        var result_pair = api_model.getLogin(context, hasKToken!!)

        if (result_pair.first == "Success_Token") {
            // shared peference
            repos_model.getHToken(result_pair.second)
            view.moveToMain()
            //메인으로 가자
        } else if (result_pair.first == "Success_NoToken") {
            // shared peference
            repos_model.getHToken(result_pair.second)
            view.setFrag(2)

        } else {
            view.setFrag(1)
            view.toast(result_pair.second)
        }
    }

    override fun updateUserIpt(loginUserIpt: LoginUserIpt,context: Context) {
        //서버에 사용 정보 POST
        repos_model.updateUserInfo(loginUserIpt,context)
        view.moveToMain()
    }
}
