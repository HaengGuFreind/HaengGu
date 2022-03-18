package com.example.haenggu.main.home

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.haenggu.R
import com.example.haenggu.databinding.ActivityEventFilterBinding

class EventFilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventFilterBinding
    private val item = listOf("동아리행사", "박람회", "컨퍼런스", "연극/뮤지컬", "전시", "페스티벌", "콘서트", "기타")

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(R.anim.vertical_enter, R.anim.none)

        setSupportActionBar(binding.toolbarLayout)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_refresh)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.elevation = 5F

        val spinner1 = binding.spTag1
        val spinner2 = binding.spTag2
        val spinner3 = binding.spTag3
        val adapter = ArrayAdapter.createFromResource(this, R.array.tags, R.layout.item_spinner)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, R.layout.item_spinner, item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter
        spinner3.adapter = adapter


        binding.ivClose.setOnClickListener{
            overridePendingTransition(R.anim.none, R.anim.vertical_exit)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->{
            }
        }
        return super.onOptionsItemSelected(item)
    }
}