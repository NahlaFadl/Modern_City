package com.example.modern_city.ui.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.modern_city.R

class CategoryDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_details)
        supportActionBar?.hide()


    }
}