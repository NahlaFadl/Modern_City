package com.example.modern_city

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.modern_city.ui.auth.CraftsmanRegisterActivity
import com.example.modern_city.ui.auth.RegisterActivity
import kotlinx.android.synthetic.main.activity_user_type.*

class UserTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_type)

        user_btn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        craft_btn.setOnClickListener {
            val intent = Intent(this, CraftsmanRegisterActivity::class.java)
            startActivity(intent)
        }
    }
}