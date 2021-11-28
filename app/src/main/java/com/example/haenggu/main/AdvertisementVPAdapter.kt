package com.example.haenggu.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.haenggu.R
import kotlinx.android.synthetic.main.list_mainactivity_advertisementimg.view.*

class AdvertisementVPAdapter(advertisementList: ArrayList<Int>) : RecyclerView.Adapter<AdvertisementVPAdapter.PagerViewHolder>() {
    var item = advertisementList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.idol.setImageResource(item[position])
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.list_mainactivity_advertisementimg, parent, false)){

        val idol = itemView.viewpager_mainactivity_adimgview!!
    }
}