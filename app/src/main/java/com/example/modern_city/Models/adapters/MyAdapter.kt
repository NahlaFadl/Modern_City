//package com.example.modern_city.Models.adapters
//
////import android.support.v4.app.FragmentPagerAdapter
//import android.content.Context;
////import android.support.v4.app.Fragment
////import android.support.v4.app.FragmentManager
//import androidx.fragment.app.Fragment
////import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentPagerAdapter
//import com.example.modern_city.ui.fragment.FavouriteCraftsFragment
//import com.example.modern_city.ui.fragment.FavouritePlacesFragment
//
//class MyAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {
//
//    // this is for fragment tabs
//    override fun getItem(position: Int): Fragment? {
//        when (position) {
//            0 -> {
//                //  val homeFragment: HomeFragment = HomeFragment()
//                return FavouritePlacesFragment()
//            }
//            1 -> {
//                return FavouriteCraftsFragment()
//            }
//            else -> return null
//        }
//    }
//
//    // this counts total number of tabs
//    override fun getCount(): Int {
//        return totalTabs
//    }
//}