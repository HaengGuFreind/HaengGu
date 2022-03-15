package com.example.haenggu.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.haenggu.data.api.KaKaoAPI
import com.example.haenggu.data.remote.datasources.SchoolItem
import com.example.haenggu.data.remote.datasources.UserInfo
import com.example.haenggu.data.repository.LoginRepository

class LoginPresenter (view:LoginContract.LoginView):LoginContract.LoginPresenter,LoginContract.LoginInterator{
    private var view: LoginContract.LoginView = view
    private var api_model: LoginContract.KakaoAPIModel = KaKaoAPI(this)
    private var repos_model: LoginContract.LoginRepo = LoginRepository(this)
    var major_list=ArrayList<SchoolItem>()
    var net3:Boolean = false

    override fun hasToken(context: Context){
        // 토큰 확인
        Log.d("hastoken","토큰없음")
        api_model.getToken(context)
    }

    override fun onLogin(context: Context) {
        api_model.getLogin(context)
    }

    override fun resultToken(hasToken:String,context: Context) {
        if (hasToken  == "NoToken"){ // 로그인 필요
            Log.d("hastoken","토큰없음")
            view.setFrag(1)
        }else{
            Log.d("hastoken","토큰있음")
            repos_model.getHToken(hasToken,context)
            view.setFrag(1)
            //view.moveToMain()
        }
    }

    override fun resultKLogin(hasKToken: String, context: Context) {
        if (hasKToken == "fail"){
            view.toast("로그인에 실패 하셨습니다.")
            view.setFrag(1)
        }else{
            repos_model.getHToken(hasKToken,context)
        }
    }

    override fun resultHLogin(net:Boolean, role:String) {
        // 성공시 서버 토큰 받아와
        if (net) {
            Log.d("onLogin", "success")
            if (role == "ROLE_GUEST") {
                view.setFrag(2)
            } else {
                // 메인으로 이동
                view.setFrag(1)
                //view.moveToMain()
            }
        } else {
            Log.d("onLogin", "fail")

            // 실패
            view.toast("로그인에 실패 하셨습니다.")
            // 실패 통보??할?말?
            view.setFrag(1)

        }
    }

    override fun resultSchool(net:Boolean, school_item: ArrayList<SchoolItem>) {
        net3 = net
        major_list = school_item
        Log.d("요잉닝", net3.toString())
    }

    override fun updateUserIpt(userInfo: UserInfo, context: Context) {
        //서버에 사용 정보 POST
        repos_model.updateUserInfo(userInfo,context)
        view.moveToMain()
    }

    override fun getSchoolInfo(school_name: String, context: Context) {
        repos_model.getSchool(school_name, context)
    }

    override fun getMajorList(): Pair<Boolean, ArrayList<SchoolItem>> {
        Log.d("요잉닝2", net3.toString())
        Log.d("요잉닝3", major_list.toString())
        return Pair(net3,major_list)
    }

}
