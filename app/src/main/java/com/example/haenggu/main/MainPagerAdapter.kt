package com.example.haenggu.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.haenggu.main.chat.ChatFragment
import com.example.haenggu.main.find.FindFragment
import com.example.haenggu.main.home.HomeFragment
import com.example.haenggu.main.mypage.MypageFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> FindFragment()
            2 -> ChatFragment()
            else -> MypageFragment()
        }
    }

    override fun getCount(): Int = 4
}