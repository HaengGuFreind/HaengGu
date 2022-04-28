package com.example.haenggu.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.haenggu.data.remote.datasources.EventData
import com.example.haenggu.databinding.ItemCategoryBinding

class EventListAdapter(val itemClick: (EventData, View) -> Unit) : RecyclerView.Adapter<EventViewHolder>() {
    var datas = mutableListOf<EventData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder{
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}

class EventViewHolder(private val binding: ItemCategoryBinding, val itemClick: (EventData, View) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(myData: EventData){
        itemView.setOnClickListener{itemClick(myData, itemView)}
        binding.tvEvent.text = myData.title
        binding.tvDescription.text = myData.description
        binding.tvDate.text = myData.started_date.substring(0,10) + " ~ " + myData.ended_date.substring(0,10)
        if(myData.tag.isNotEmpty()){
            binding.tvTag1.text = myData.tag[0]
            binding.tvTag2.text = myData.tag[1]
            binding.tvTag3.text = myData.tag[2]
        }
        if(myData.event_image_uri.isNotEmpty()) Glide.with(itemView).load(myData.event_image_uri[0]).into(binding.ivEvent)
        binding.tvLike.text = myData.favorite.toString()
    }
}