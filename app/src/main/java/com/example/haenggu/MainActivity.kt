package com.example.haenggu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.dots_indicator)
//        val viewPager = findViewById<ViewPager>(R.id.activity_main_dots_indicator)
//        val adapter = ViewPagerAdapter()
//        viewPager.adapter = adapter
//        dotsIndicator.setViewPager(viewPager)
    }
}