package com.example.modern_city

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_regester.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regester)

        register_txtAct.setOnClickListener {
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}