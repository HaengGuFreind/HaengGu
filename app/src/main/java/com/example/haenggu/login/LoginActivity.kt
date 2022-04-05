package com.example.haenggu.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haenggu.R
import com.example.haenggu.data.remote.datasources.SchoolItem
import com.example.haenggu.data.remote.datasources.UserInfo
import com.example.haenggu.databinding.ActivityLoginBinding
import com.example.haenggu.main.MainActivity
import com.kakao.sdk.user.model.User

class LoginActivity : AppCompatActivity(), LoginContract.LoginView {
    private var presenter: LoginContract.LoginPresenter ?= null
    lateinit var binding:ActivityLoginBinding
    private lateinit var useript: Fragment
    private lateinit var school: Fragment
    private lateinit var major: Fragment

    private val viewModel: SharedViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                SharedViewModel() as T
        }
    }
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


        useript = UserIptFragment()
        school = SchoolSearchFragment()
        major = MajorSearchFragment()

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
                transaction.replace(R.id.frameLayout_login,useript)
                transaction.add(R.id.frameLayout_login,school)
                transaction.add(R.id.frameLayout_login,major)
                transaction.show(useript)
                transaction.hide(school)
                transaction.hide(major)
                transaction.commit()
            }
            3->{
                transaction.hide(useript)
                transaction.show(school)
                transaction.hide(major)
                transaction.commit()
            }
            4->{
                transaction.hide(useript)
                transaction.hide(school)
                transaction.show(major)
                transaction.commit()
            }
            5->{
                transaction.show(useript)
                transaction.hide(school)
                transaction.hide(major)
                transaction.commit()
            }
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
    }

    fun getmlist(): Pair<Boolean, ArrayList<SchoolItem>> {
        return presenter?.getMajorList()!!
    }

    override fun moveToMain() {
        startActivity(Intent(this, MainActivity::class.java))//메인 페이지로 이동
        finish()
    }


}