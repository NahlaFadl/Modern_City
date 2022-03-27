package com.example.modern_city

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.modern_city.Fragment.AboutUsFragment
import com.example.modern_city.Fragment.FavoriteFragment
import com.example.modern_city.Fragment.HomeFagment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private val homeFragment=HomeFagment()
    private val favoriteFragment=FavoriteFragment()
    private val aboutUsFragment=AboutUsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(homeFragment)


       /* btn .setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.favorite -> {
                    replaceFragment(favoriteFragment)
                    true
                }
                R.id.home -> {
                    replaceFragment(homeFragment)
                    true
                }
                R.id.aboutUs -> {
                    replaceFragment(aboutUsFragment)
                    true
                }


                else -> false
            }
        }*/
    }

    public fun replaceFragment(fragment:Fragment){
        if (fragment!=null){
            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer,fragment)
            transaction.commit()

        }




    }
}