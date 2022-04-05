package com.example.haenggu.main.find

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.haenggu.R
import com.example.haenggu.data.local.SharedManager
import com.example.haenggu.data.remote.datasources.ResponseBoardDetail
import com.example.haenggu.data.remote.services.RetrofitInstance
import com.example.haenggu.databinding.ActivityFindDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindDetailBinding
    private val service = RetrofitInstance.api
    private lateinit var idx: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarLayout)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_refresh)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        idx = intent.getStringExtra("idx").toString()
        initView()
    }

    fun initView(){
        val sharedManager: SharedManager by lazy { SharedManager(this) }
        service.getBoardDetail(sharedManager.getHToken(), idx).enqueue(object :
            Callback<ResponseBoardDetail>{
            override fun onResponse(
                call: Call<ResponseBoardDetail>,
                response: Response<ResponseBoardDetail>
            ) {
                if(response.isSuccessful){
                    val data = response.body()!!
                    binding.activityFindDetailTitle.text = data.title
                    binding.activityFindDetailEventTitle.text = data.event.title
                    binding.activityFindDetailDate.text = data.schedule
                    binding.activityFindDetailContent.text = data.content
                }
            }

            override fun onFailure(call: Call<ResponseBoardDetail>, t: Throwable) {
            }

        })
    }
}