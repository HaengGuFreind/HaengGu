package com.example.haenggu.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.haenggu.R
import com.example.haenggu.databinding.ActivityMainBinding
import com.example.haenggu.main.chat.ChatFragment
import com.example.haenggu.main.find.FindFragment
import com.example.haenggu.main.home.HomeFragment
import com.example.haenggu.main.mypage.MypageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val home by lazy { HomeFragment() }
    private val find by lazy { FindFragment() }
    private val chat by lazy { ChatFragment() }
    private val mypage by lazy { MypageFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBarLayout.outlineProvider = null
        binding.activityMainBn.run {
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.menu_home -> {
                        changeFragment(home)
                    }
                    R.id.menu_find -> {
                        changeFragment(find)
                    }
                    R.id.menu_chat -> {
                        changeFragment(chat)
                    }
                    else -> {
                        changeFragment(mypage)
                    }
                }
                true
            }
            selectedItemId = R.id.menu_home
        }
    }
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main_frame, fragment)
            .commit() }

}