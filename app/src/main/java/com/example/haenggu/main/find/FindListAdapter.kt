package com.example.haenggu.main.find

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.haenggu.data.remote.datasources.BoardData
import com.example.haenggu.data.remote.datasources.EventData
import com.example.haenggu.databinding.ItemCategoryBinding
import com.example.haenggu.databinding.ItemFindHaengguBinding

class FindListAdapter(val itemClick: (BoardData, View) -> Unit) : RecyclerView.Adapter<FindViewHolder>() {
    var datas = mutableListOf<BoardData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindViewHolder {
        val binding = ItemFindHaengguBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FindViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: FindViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}

class FindViewHolder(private val binding: ItemFindHaengguBinding, val itemClick: (BoardData, View) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mydata: BoardData){
        binding.fragmentFindTvEventname.text = mydata.title
        binding.fragmentFindTvName.text = mydata.title
        binding.fragmentFindTvPeriod.text = mydata.schedule
        binding.fragmentFindTvName.text = mydata.user.username
        if(mydata.user.profile_image.isNotEmpty()) Glide.with(itemView).load(mydata.user.profile_image).into(binding.fragmentFindCiv)
        //if(mydata.user.profile_image.isNotEmpty()) Glide.with(itemView).load(mydata.user.profile_image).into(binding.fragmentFindCiv)
    }
}
