package com.example.haenggu.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class EventViewPagerAdapger(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 8

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0-> EventListFragment.newInstance("CLUB","")
            1-> EventListFragment.newInstance("JOBFAIR","")
            2-> EventListFragment.newInstance("CONFERENCE","")
            3-> EventListFragment.newInstance("THEATER","")
            4-> EventListFragment.newInstance("EXHIBITION","")
            5-> EventListFragment.newInstance("FESTIVAL","")
            6-> EventListFragment.newInstance("CONCERT","")
            else -> EventListFragment.newInstance("ETC","")
        }
    }
}