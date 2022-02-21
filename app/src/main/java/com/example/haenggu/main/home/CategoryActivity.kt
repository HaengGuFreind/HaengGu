package com.example.haenggu.main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.haenggu.databinding.ActivityCategoryBinding
import com.google.android.material.tabs.TabLayoutMediator

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    val tabName = listOf("동아리행사", "박람회", "컨퍼런스", "연극/뮤지컬", "전시", "페스티벌", "콘서트", "기타")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapter = EventViewPagerAdapger(this,"category")
        binding.vpEvent.apply {
            binding.vpEvent.adapter = adapter
        }
        binding.vpEvent.offscreenPageLimit = 3
        TabLayoutMediator(binding.tablayout, binding.vpEvent) { tab, position ->
            tab.text = tabName[position]
        }.attach()
    }
}