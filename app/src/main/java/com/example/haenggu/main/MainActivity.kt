package com.example.haenggu.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.haenggu.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_vp.adapter = MainPagerAdapter(supportFragmentManager)
        activity_main_vp.offscreenPageLimit = 2
    }
}