package com.example.haenggu.main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.haenggu.databinding.FragmentHomeBinding
import com.example.haenggu.main.MainActivity
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val tabName = listOf("동아리행사", "박람회", "컨퍼런스", "연극/뮤지컬", "전시", "페스티벌", "콘서트", "기타")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentHomeBtnExhibition.setOnClickListener {
            startActivity(Intent(context as MainActivity, CategoryActivity::class.java))
        }

        var adapter = EventViewPagerAdapger(context as FragmentActivity,"category")
        binding.vpEvent.apply {
            binding.vpEvent.adapter = adapter
        }
        binding.vpEvent.offscreenPageLimit = 3
        TabLayoutMediator(binding.tablayout, binding.vpEvent) { tab, position ->
            tab.text = tabName[position]
        }.attach()
    }
}