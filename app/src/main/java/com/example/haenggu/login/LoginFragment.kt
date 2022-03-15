package com.example.haenggu.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haenggu.databinding.FragmentLoginLoginBinding
import com.example.haenggu.databinding.FragmentLoginSplashBinding
import com.example.haenggu.main.MainActivity
import com.example.haenggu.main.home.EventDetailActivity
import kotlinx.android.synthetic.main.fragment_login_login.*

class LoginFragment: Fragment() {

    private var _binding: FragmentLoginLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentLoginBtnKakaoLogin.setOnClickListener {
            val lActivity = activity as LoginActivity
            lActivity.clickKaKaoLogin()
        }
        fragment_login_nomember.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}