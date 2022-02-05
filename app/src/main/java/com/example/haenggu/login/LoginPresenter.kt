package com.example.haenggu.login

import android.content.Context
import com.example.haenggu.data.api.KaKaoAPI
import com.example.haenggu.data.remote.datasources.LoginUserIpt
import com.example.haenggu.data.repository.LoginRepository

class LoginPresenter (view:LoginContract.LoginView):LoginContract.LoginPresenter{
    private var view: LoginContract.LoginView = view
    private var api_model: LoginContract.KakaoAPIModel = KaKaoAPI()
    private var repos_model: LoginContract.LoginRepo = LoginRepository()
    private var hasKToken : String ?= null

    override fun isLogin(context: Context): String {
        // 토큰 확인
        hasKToken = api_model.hasToken()
        if (hasKToken == "False" || hasKToken == "NoToken"){ // 로그인 필요
            view.setFrag(1)
        }else{
            // 모델 -> 서버에 정보 전달이 필요할지 의논
            // 뷰 -> 메인으로 이동
            repos_model.getHToken(hasKToken!!,context)
            view.moveToMain()
        }
        TODO("Not yet implemented")
    }

    override fun onLogin(context: Context) {
        var result_pair = api_model.getLogin(context, hasKToken!!)

        if (result_pair.first == "Success_Token") {
            // 기존 회원의 토큰
                // 서버에 보내기
            repos_model.getHToken(result_pair.second,context)
            // 메인으로 이동
            view.moveToMain()
        } else if (result_pair.first == "Success_NoToken") {

            repos_model.getHToken(result_pair.second,context)
            // 정보입력으로 이동
            view.setFrag(2)

        } else {
            // 실패
            view.setFrag(1)
            // 실패 이유 띄우기
            view.toast(result_pair.second)
        }
    }

    override fun updateUserIpt(loginUserIpt: LoginUserIpt, context: Context) {
        //서버에 사용 정보 POST
        repos_model.updateUserInfo(loginUserIpt,context)
        view.moveToMain()
    }
}
