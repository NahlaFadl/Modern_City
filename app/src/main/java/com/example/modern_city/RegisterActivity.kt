package com.example.modern_city

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.API
import com.example.modern_city.API_SERVIECS.RegisterResponse
import com.example.modern_city.Fragment.HomeFagment
import kotlinx.android.synthetic.main.activity_regester.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    val url="https://btmteamwork.com/sys/mass3ood/new_modern_city/public/"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regester)

        register_txtAct.setOnClickListener {




            val retrofit=Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api:API=retrofit.create(API::class.java)

            val call=api.register(1,edt_register_Fname.text.toString().trim()
                ,edt_register_Lname.text.toString().trim(),edt_register_email.text.toString().trim()
                ,"mail",edt_register_password.text.toString().trim()
                ,"minia","01254005")

            call.enqueue(object :Callback<RegisterResponse>{
                override fun onResponse(
                    call: Call<RegisterResponse>?,
                    response: Response<RegisterResponse>?)
                {

                    Toast.makeText(this@RegisterActivity,response?.body()?.status.toString(),Toast.LENGTH_LONG).show()
                    val intenthome=Intent(this@RegisterActivity,HomeFagment::class.java)
                    startActivity(intenthome)

                }

                override fun onFailure(call: Call<RegisterResponse>?, t: Throwable?) {
                    Toast.makeText(this@RegisterActivity,"Failer",Toast.LENGTH_LONG).show()

                }

            })








        }
    }


}