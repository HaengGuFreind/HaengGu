package com.example.haenggu.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.haenggu.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = nav_host_fragment_login.findNavController()

        activity_main_vpAdvertisement.adapter = AdvertisementVPAdapter(getAdvertisementImg())
        activity_main_vpAdvertisement.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        dots_indicator.setViewPager2(activity_main_vpAdvertisement)


        val newEventsVPAdapter = NewEventsVPAdapter(supportFragmentManager)
        newEventsVPAdapter.addFragment(NewEvent1Fragment(),"컨퍼런스")
        newEventsVPAdapter.addFragment(NewEvent2Fragment(),"잡페어")
        newEventsVPAdapter.addFragment(NewEvent3Fragment(),"동아리행사")
        newEventsVPAdapter.addFragment(NewEvent4Fragment(),"기타")
        newEventsVPAdapter.addFragment(NewEvent5Fragment(),"전시")
        newEventsVPAdapter.addFragment(NewEvent6Fragment(),"페스티벌")

        activity_main_vpNewEvents.adapter = newEventsVPAdapter
        activity_main_tblNewEvent.setupWithViewPager(activity_main_vpNewEvents)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)

    }

    //뷰페이저에 들어갈 것
    private fun getAdvertisementImg():ArrayList<Int>{
        return arrayListOf<Int>(R.drawable.image_viewpager_advertisement,R.drawable.image_viewpager_advertisement,R.drawable.image_viewpager_advertisement)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnavi_all_image1 -> {
                return true
            }
            R.id.btnavi_all_image2 -> {
                return true
            }
            R.id.btnavi_all_image3 -> {
                return true
            }
            R.id.btnavi_all_image4 -> {
                return true
            }

        }
        return false
    }
}