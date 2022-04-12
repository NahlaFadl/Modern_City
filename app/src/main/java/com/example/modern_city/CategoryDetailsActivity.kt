package com.example.modern_city

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CategoryDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_details)
        supportActionBar?.hide()


    }
}