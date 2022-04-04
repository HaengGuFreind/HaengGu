package com.example.haenggu.main.find

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.haenggu.R
import com.example.haenggu.data.local.SharedManager
import com.example.haenggu.databinding.ActivityFindDetailBinding

class FindDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarLayout)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_refresh)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        initView()
    }

    fun initView(){

    }
}