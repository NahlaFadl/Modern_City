package com.example.modern_city

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        txt_login.setOnClickListener {
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        txt_register.setOnClickListener {
            val intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}