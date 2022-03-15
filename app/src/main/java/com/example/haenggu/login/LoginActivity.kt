package com.example.haenggu.login

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.haenggu.R
import com.example.haenggu.data.remote.datasources.SchoolItem
import com.example.haenggu.data.remote.datasources.UserInfo
import com.example.haenggu.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginContract.LoginView {
    private var presenter: LoginContract.LoginPresenter ?= null
    lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = LoginPresenter(this)
        
        setFrag(0)
        Handler().postDelayed({
            presenter?.hasToken(applicationContext)
        }, 500)


    }


    override fun setFrag(fragNum : Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(fragNum)
        {
            0->{
                Log.d("herer1","SplashFragment")
                transaction.add(R.id.frameLayout_login,SplashFragment())
            }
            1->{
                Log.d("herer1","LoginFragment")
                transaction.replace(R.id.frameLayout_login,LoginFragment()).commit()
            }
            2->{
                transaction.replace(R.id.frameLayout_login,UserIptFragment()).commit()
            }
//            3->{
//                transaction.replace(R.id.frameLayout_login,SchoolSearchFragment()).addToBackStack(null).commit()
//            }
        }
    }

    override fun toast(string: String) {
        Toast.makeText(this,string, Toast.LENGTH_LONG).show()

    }

    // 카카오 로그인 버튼 클릭시
    fun clickKaKaoLogin(){
        presenter?.onLogin(applicationContext)
    }

    fun getUserIpt(userInfo: UserInfo){
        presenter?.updateUserIpt(userInfo,applicationContext)
        finish()
//        moveToMain()
    }

    fun getsinfo(school_name: String){
        presenter?.getSchoolInfo(school_name,this)
    }
    fun getmlist(): Pair<Boolean, ArrayList<SchoolItem>> {
        return presenter?.getMajorList()!!
    }


    override fun moveToMain() {
        //        startActivity(Intent(this,MainActivity::class.java))//메인 페이지로 이동
        finish()
    }

}