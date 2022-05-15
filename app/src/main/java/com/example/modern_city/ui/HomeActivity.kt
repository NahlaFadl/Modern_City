package com.example.modern_city.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.modern_city.Fragment.HomeFagment
import com.example.modern_city.R
import com.example.modern_city.ui.fragment.AboutUsFragment
import com.example.modern_city.ui.fragment.FavoritesFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private val homeFragment=HomeFagment()
    private val favoritesFragment= FavoritesFragment()
    private val aboutUsFragment= AboutUsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(homeFragment)

        btn_bottomNavigation .setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.favorite -> {
                    replaceFragment(favoritesFragment)
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
        }
    }

     fun replaceFragment(fragment:Fragment){
        if (fragment!=null){
            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer,fragment)
            transaction.commit()

        }




    }
}