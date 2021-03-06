package com.example.haenggu.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.haenggu.R
import com.example.haenggu.data.local.SharedManager
import com.example.haenggu.databinding.ActivityMainBinding
import com.example.haenggu.login.LoginActivity
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
    private val sharedManager: SharedManager by lazy { SharedManager(this) }

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
                        if(sharedManager.getHToken() != "") changeFragment(find)
                        else {
                            val customDialog = CustomDialog(this@MainActivity)
                            customDialog.setOnOKClickedListener {
                                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                            }
                            customDialog.start("서비스 이용을 위해 로그인 해주세요\uD83D\uDE06", "좋아요", "더 둘러볼게요")
                        }
                    }
                    R.id.menu_chat -> {
                        if(sharedManager.getHToken() != "") changeFragment(chat)
                        else {
                            val customDialog = CustomDialog(this@MainActivity)
                            customDialog.setOnOKClickedListener {
                                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                            }
                            customDialog.start("서비스 이용을 위해 로그인 해주세요\uD83D\uDE06", "좋아요", "더 둘러볼게요")
                        }
                    }
                    else -> {
                        if(sharedManager.getHToken() != "") changeFragment(mypage)
                        else {
                            val customDialog = CustomDialog(this@MainActivity)
                            customDialog.setOnOKClickedListener {
                                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                            }
                            customDialog.start("서비스 이용을 위해 로그인 해주세요\uD83D\uDE06", "좋아요", "더 둘러볼게요")
                        }
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