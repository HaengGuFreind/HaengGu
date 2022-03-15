package com.example.haenggu.data.repository

import android.content.Context
import android.util.Log
import com.example.haenggu.data.local.SharedManager
import com.example.haenggu.data.remote.datasources.*
import com.example.haenggu.data.remote.services.RetrofitInstance
import com.example.haenggu.login.LoginContract
import com.example.haenggu.login.LoginPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository (var interator: LoginContract.LoginInterator?): LoginContract.LoginRepo {
    fun unregister() {
        interator = null
    }

    override fun getHToken(ktoken: String, context: Context){
        val sharedManager: SharedManager by lazy { SharedManager(context) }

        RetrofitInstance.api.getHToken(ktoken).enqueue(object : Callback<Login>{
            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                if(response.isSuccessful){
                    Log.d("respone_body", response.body().toString())
                    Log.d("respone_coede", response.code().toString())
                    Log.d("respone_message", response.message())
                    sharedManager.saveHToken(response.body()!!.token)
                    //원하는 것만 꺼내기
                    interator?.resultHLogin(true, response.body()!!.role_type)

                }else{
                    Log.d("respone_body", response.body().toString())
                    Log.d("respone_coede", response.code().toString())
                    Log.d("respone_message", response.message())
                    interator?.resultHLogin(false, "")
                }
            }
            override fun onFailure(call: Call<Login>, t: Throwable) {
                //실패시
                Log.d("respone_body", "통신실패")
                interator?.resultHLogin(false, "")
            }
        })
    }

    override fun updateUserInfo(userInfo:UserInfo,context: Context){

        val sharedManager: SharedManager by lazy { SharedManager(context) }
        var htoken = sharedManager.getHToken()

        RetrofitInstance.api.updateUseript(htoken, userInfo).enqueue(object : Callback<RRUserInfo> {
            override fun onResponse(call: Call<RRUserInfo>, response: Response<RRUserInfo>) {
                if(response.isSuccessful){
                    Log.d("respone_body", response.body().toString())
                    Log.d("respone_coede", response.code().toString())
                    Log.d("respone_message", response.message())
                }else{
                    Log.d("respone_body", response.body().toString())
                    Log.d("respone_coede", response.code().toString())
                    Log.d("respone_message", response.message())
                }
            }

            override fun onFailure(call: Call<RRUserInfo>, t: Throwable) {
                //실패시
                Log.d("respone_body", "통신실패")

            }
        })
    }

    override fun getSchool(school_name: String, context: Context) {

        RetrofitInstance.api.getSchool(school_name).enqueue(object : Callback<School>{
            override fun onResponse(call: Call<School>, response: Response<School>) {
                if(response.isSuccessful){
                    Log.d("respone_body", response.body().toString())
                    Log.d("respone_coede", response.code().toString())
                    Log.d("respone_message", response.message())
                    //원하는 것만 꺼내기
                    interator?.resultSchool(true, response.body()!!)

                }else{
                    Log.d("respone_body", response.body().toString())
                    Log.d("respone_coede", response.code().toString())
                    Log.d("respone_message", response.message())
                    var item=ArrayList<SchoolItem>()
                    interator?.resultSchool(false, item)

                }
            }
            override fun onFailure(call: Call<School>, t: Throwable) {
                //실패시
                Log.d("respone_body", "통신실패")
            }
        })
    }

}
