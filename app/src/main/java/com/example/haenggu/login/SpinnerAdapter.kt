package com.example.haenggu.login

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.example.haenggu.databinding.SpinnerItemBinding

class SpinnerAdapter (
    context: Context,
    @LayoutRes
    private val resId: Int,
    private val values: MutableList<SpinnerModel>
    ) : ArrayAdapter<SpinnerModel>(context, resId, values) {

        override fun getCount() = values.size


        override fun getItem(position: Int) = values[position]

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val binding = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val model = values[position]
            try {
                if (position== 0) {
                    binding.spinnerText1.text = model.textString
                    binding.spinnerText1.setTextColor(Color.parseColor("#9b9b9b"))
                }else{
                    binding.spinnerText1.text = model.textString
                    binding.spinnerText1.setTextColor(Color.parseColor("#383838"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return binding.root
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            val binding = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val model = values[position]
            try {
                binding.spinnerText1.text = model.textString

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return binding.root
        }
}