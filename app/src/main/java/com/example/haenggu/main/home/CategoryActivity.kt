package com.example.haenggu.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.haenggu.R
import com.example.haenggu.databinding.ActivityCategoryBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private val tabName = listOf("동아리행사", "박람회", "컨퍼런스", "연극/뮤지컬", "전시", "페스티벌", "콘서트", "기타")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarLayout)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        var adapter = EventViewPagerAdapger(this,"category")
        binding.vpEvent.apply {
            binding.vpEvent.adapter = adapter
        }
        binding.vpEvent.offscreenPageLimit = 3
        TabLayoutMediator(binding.tablayout, binding.vpEvent) { tab, position ->
            tab.text = tabName[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_arrange -> {

            }
            R.id.menu_filter -> {
                startActivity(Intent(this, EventFilterActivity::class.java))

            }
        }
        return super.onOptionsItemSelected(item)
    }
}