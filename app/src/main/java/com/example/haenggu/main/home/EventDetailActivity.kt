package com.example.haenggu.main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.haenggu.data.remote.datasources.ResponseEventDetail
import com.example.haenggu.data.remote.services.RetrofitInstance
import com.example.haenggu.databinding.ActivityEventDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding
    private val service = RetrofitInstance.api
    private lateinit var idx: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idx = intent.getStringExtra("idx").toString()

        service.getEvenDetail(idx).enqueue(object : Callback<ResponseEventDetail> {//Callback등록. Retrofit의 Callback을 import 해줘야 함!
            override fun onFailure(call: Call<ResponseEventDetail>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseEventDetail>,
                response: Response<ResponseEventDetail>
            ) {
                if(response.isSuccessful){
                    var data = response.body()
                    Glide.with(this@EventDetailActivity).load(data!!.image_url[0]).into(binding.activityEvectDetailIv)
                    binding.tvTag1.text = data.tag[0]
                    binding.tvTag2.text = data.tag[1]
                    //binding.tvTag3.text = data.tag[2]
                    binding.activityEvectDetailTvTitle.text = data.title
                    binding.activityEvectDetailTvContent.text = data.description
                    binding.activityEvectDetailTvDate.text = data.reservation_ended_date
                    binding.activityEvectDetailTvStart.text = data.started_date
                    binding.activityEvectDetailTvEnd.text = data.ended_date
                    //binding.activityEvectDetailTvTime.text = data.time
                    binding.activityEvectDetailTvLocation.text = data.event_location
                }
            }
        })
    }
}