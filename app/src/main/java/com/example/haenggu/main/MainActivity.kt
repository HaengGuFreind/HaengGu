package com.example.haenggu.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.haenggu.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_vp.adapter = MainPagerAdapter(supportFragmentManager)
        activity_main_vp.offscreenPageLimit = 2
        activity_main_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                // 네비게이션 메뉴 아이템 체크
                activity_main_bn.menu.getItem(position).isChecked = true
            }
        })
        activity_main_bn.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> activity_main_vp.currentItem = 0
                R.id.menu_find -> activity_main_vp.currentItem = 1
                R.id.menu_chat -> activity_main_vp.currentItem = 2
                else -> activity_main_vp.currentItem = 3
            }
            true
        }
    }
}