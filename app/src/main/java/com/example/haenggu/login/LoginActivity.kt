package com.example.haenggu.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.haenggu.R
import com.example.haenggu.data.remote.datasources.LoginUserIpt
import com.example.haenggu.main.MainActivity
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity :AppCompatActivity(), LoginContract.LoginView {
    private var presenter:LoginPresenter ?= null
    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        navController = nav_host_fragment_login.findNavController()

        setFrag(1)
        presenter!!.isLogin()


    }

    override fun setFrag(fragNum : Int){
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum)
        {
            0->{
                ft.replace(R.id.nav_host_fragment_login,SplashFragment()).commit()
            }
            1->{
                ft.replace(R.id.nav_host_fragment_login,LoginFragment()).commit()
            }
            2->{
                ft.replace(R.id.nav_host_fragment_login,UserIptFragment()).commit()
            }
            3->{
                ft.replace(R.id.nav_host_fragment_login,SchoolSearchFragment()).commit()
            }
        }
    }

    override fun toast(string: String) {
        Toast.makeText(this,string, Toast.LENGTH_LONG).show()

    }

    // 카카오 로그인 버튼 클릭시
    fun clickKaKaoLogin(){
        presenter!!.onLogin(this)
    }

    fun getUserIpt(loginUserIpt: LoginUserIpt){
        presenter!!.updateUserIpt(loginUserIpt,this)
        finish()
        startActivity(Intent(this,MainActivity::class.java))//메인 페이지로 이동
    }

    override fun moveToMain() {
        finish()
        startActivity(Intent(this,MainActivity::class.java))//메인 페이지로 이동
    }

}