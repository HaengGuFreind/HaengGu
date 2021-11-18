package com.example.haenggu.login

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.haenggu.R

class LoginActivity: AppCompatActivity(), LoginContract.View {
    private var presenter:LoginPresenter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        val btn_Login = findViewById<Button>(R.id.btn_Login)
        btn_Login.setOnClickListener{
            presenter?.onLogin()
        }
    }

    override fun updateView() {
        TODO("Not yet implemented")
        //
    }


}