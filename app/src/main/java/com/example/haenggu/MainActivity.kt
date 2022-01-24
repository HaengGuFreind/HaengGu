package com.example.haenggu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.activity_main_dots_indicator)
        val viewPager = findViewById<ViewPager2>(R.id.activity_main_vpPromotion)
        viewPager.adapter = ViewPagerAdapter(getPromoList())
        dotsIndicator.setViewPager2(viewPager)
    }

    // 뷰 페이저에 들어갈 아이템
    private fun getPromoList(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.temp_promo1,
            R.drawable.temp_promo1,
            R.drawable.temp_promo1
        )
    }

    class ViewPagerAdapter(promoList: ArrayList<Int>) :
        RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
        var item = promoList

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PagerViewHolder((parent))

        override fun getItemCount(): Int = item.size

        override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
            holder.promo.setImageResource(item[position])
        }

        inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
            (
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_promoimageview, parent, false)
        ) {

            val promo = itemView.findViewById<ImageView>(R.id.activity_main_ivpromotion)!!
        }
    }
}
