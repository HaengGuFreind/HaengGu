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
                    Log.d("getHtoken", response.body()!!.token.toString())
                    Log.d("getHtoken2", sharedManager.getHToken())
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
        Log.d("토크보내보기", sharedManager.getHToken())
        Log.d("ss",userInfo.toString())
        Log.d("Htoken_updateuseript",sharedManager.getHToken())
//        var userid = "78996ec5-e39b-4a22-91e7-954f03c27e2a"
//        var userinfoa = HashMap<String,Any>()
//        userinfoa.put("birthday","0000-01-01")
//        userinfoa.put("category_tag", listOf("CLUB"))
//        userinfoa.put("dept_id","cc9d0c59-58ca-4c1f-9a49-28e6559a5dd8")
//        userinfoa.put("email","gracious7272@gmail.com")
//        userinfoa.put("gender","MAIL")
//        userinfoa.put("grade",4)
//        userinfoa.put("mbti","ENTJ")
//
//        userinfoa.put("region_tag",listOf("BUSAN"))
//        userinfoa.put("username","아이이")


//        @Headers("Content-Type: application/json"),
        //        RetrofitInstance.api.updateUseript("Bearer "+ htoken,"application/json","0000-01-01",  listOf("CLUB"),"cc9d0c59-58ca-4c1f-9a49-28e6559a5dd8", "gracious7272@gmail.com", "MAIL", 4,"ENTJ",listOf("BUSAN"),"아이이" ).enqueue(object : Callback<RRUserInfo> {
        RetrofitInstance.api.updateUseript("Bearer "+ htoken,userInfo).enqueue(object : Callback<RRUserInfo> {
            override fun onResponse(call: Call<RRUserInfo>, response: Response<RRUserInfo>) {
                if(response.isSuccessful){
                    Log.d("통신성공","사용자 정보 업데이트")
                    Log.d("respone_body", response.body().toString())
                    Log.d("respone_coede", response.code().toString())
                    Log.d("respone_message", response.message())
                    interator?.resultSign(true)
                }else{
                    Log.d("respone_body", "통신실패updateuserunfo2")
                    if (response.code()==400){
                        Log.v("Error code 400",response.errorBody()!!.string());
                    }
                    Log.d("respone_body", response.body().toString())
                    Log.d("respone_coede", response.code().toString())
                    Log.d("respone_message", response.message())
                    interator?.resultSign(false)
                }
            }

            override fun onFailure(call: Call<RRUserInfo>, t: Throwable) {
                //실패시
                Log.d("respone_body", "통신실패updateuserunfo1")
                interator?.resultSign(false)

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
