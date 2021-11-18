package com.example.haenggu.login

interface LoginContract {

    interface View {
        fun updateView()
    }

    interface Presenter {
    fun onLogin()
    fun callMain()
    }

    interface Model {
        fun isLogin(tokenbyHaengGu: String)

    }


}