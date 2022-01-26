package com.example.haenggu.login

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.haenggu.R
import com.example.haenggu.data.remote.datasources.LoginUserIpt
import com.example.haenggu.databinding.ActivityLoginBinding
import com.example.haenggu.databinding.FragmentLoginSplashBinding

class LoginActivity : AppCompatActivity(), LoginContract.LoginView {
    private var presenter:LoginPresenter ?= null
    lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        setSupportActionBar(toolbar)//커스텀한 toolbar를 액션바로 사용
//        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.

        setFrag(0)
        presenter!!.isLogin(applicationContext)


    }

//    //액션버튼 메뉴 액션바에 집어 넣기
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbar_menu, menu)
//        return true
//    }
//
//    //액션버튼 클릭 했을 때
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item?.itemId){
//            R.id. -> {
//                //검색 버튼 눌렀을 때
//                Toast.makeText(applicationContext, "검색 이벤트 실행", Toast.LENGTH_LONG).show()
//                return super.onOptionsItemSelected(item)
//            }
//            R.id.action_share -> {
//                //공유 버튼 눌렀을 때
//                Toast.makeText(applicationContext, "공유 이벤트 실행", Toast.LENGTH_LONG).show()
//                return super.onOptionsItemSelected(item)
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

    override fun setFrag(fragNum : Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(fragNum)
        {
            0->{
                transaction.add(R.id.fragment_login_splash,SplashFragment())
            }
            1->{
                transaction.replace(R.id.fragment_login_login,LoginFragment()).commit()
            }
            2->{
                transaction.replace(R.id.fragment_login_useript,UserIptFragment()).addToBackStack(null).commit()
            }
//            3->{
//                transaction.replace(R.id.nav_host_fragment_login,SchoolSearchFragment()).commit()
//            }
        }
        transaction.commit()
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
//        startActivity(Intent(this,MainActivity::class.java))//메인 페이지로 이동
    }

    override fun moveToMain() {
        //        startActivity(Intent(this,MainActivity::class.java))//메인 페이지로 이동
        finish()
    }

}