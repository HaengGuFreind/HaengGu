package com.example.haenggu.data.repository

import android.content.Context
import com.example.haenggu.data.local.SharedManager
import com.example.haenggu.data.remote.datasources.LoginUserIpt
import com.example.haenggu.data.remote.services.HToken
import com.example.haenggu.data.remote.services.UserIpt
import com.example.haenggu.data.remote.services.ServerAPI
import com.example.haenggu.login.LoginContract
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository : LoginContract.LoginRepo {

    override fun getHToken(ktoken: String, context: Context): String {
        //서버로 부터 서버  토큰 요청 후 저장
        val sharedManager: SharedManager by lazy { SharedManager(context) }
        val apiclient_htoken = ServerAPI.client.create(HToken::class.java)
        apiclient_htoken.requestHtoken(ktoken).enqueue(object : Callback<JSONObject> {
            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                if (response.isSuccessful()) { // <--> response.code == 200
                    // 성공 처리
                    sharedManager.saveHToken(response.raw().message())
                    //ex)
                } else { // code == 400
                    // 실패 처리
                }
            }

            override fun onFailure(call: Call<JSONObject>, t: Throwable) { // code == 500
                // 실패 처리
            }
        })
        return "Success"
    }


    override fun updateUserInfo(loginuseript: LoginUserIpt, context: Context) {
        val sharedManager: SharedManager by lazy { SharedManager(context) }
        var htoken = sharedManager.getHToken()
        val apiclient_useript = ServerAPI.client.create(UserIpt::class.java)
        apiclient_useript.updateUseript(htoken,loginuseript).enqueue(object : Callback<JSONObject>{
            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                if (response.isSuccessful()) { // <--> response.code == 200
                    // 성공 처리

                    //ex)
                } else { // code == 400
                    // 실패 처리
                }
            }

            override fun onFailure(call: Call<org.json.JSONObject>, t: Throwable) { // code == 500
                // 실패 처리
            }
        })
    }
}
