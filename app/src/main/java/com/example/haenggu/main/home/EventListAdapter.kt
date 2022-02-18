package com.example.haenggu.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.haenggu.R
import com.example.haenggu.data.remote.datasources.EventData
import com.example.haenggu.databinding.FragmentEventListBinding

class EventListAdapter(private val context : Context, val itemClick: (EventData, View) -> Unit) : RecyclerView.Adapter<EventViewHolder>() {
    var datas = mutableListOf<EventData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false)
        return EventViewHolder(itemClick)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}

class EventViewHolder(private val binding: ListCategoryBinding, val itemClick: (EventData, View) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(myData: EventData){

        itemView.setOnClickListener{itemClick(myData, itemView)}
    }
}