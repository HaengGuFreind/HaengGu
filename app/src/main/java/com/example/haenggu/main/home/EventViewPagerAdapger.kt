package com.example.haenggu.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class EventViewPagerAdapger(fa : FragmentActivity, val wh : String) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 8

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0-> EventListFragment.newInstance(wh, "CLUB")
            1-> EventListFragment.newInstance(wh, "JOBFAIR")
            2-> EventListFragment.newInstance(wh,"CONFERENCE")
            3-> EventListFragment.newInstance(wh,"THEATER")
            4-> EventListFragment.newInstance(wh,"EXHIBITION")
            5-> EventListFragment.newInstance(wh,"FESTIVAL")
            6-> EventListFragment.newInstance(wh,"CONCERT")
            else -> EventListFragment.newInstance(wh,"ETC")
        }
    }
}