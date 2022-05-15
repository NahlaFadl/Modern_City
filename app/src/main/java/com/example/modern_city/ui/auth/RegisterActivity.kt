package com.example.modern_city.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.API
//import com.example.modern_city.API_SERVIECS.RegisterResponse
import com.example.modern_city.API_SERVIECS.UserRegister
import com.example.modern_city.Fragment.HomeFagment
import com.example.modern_city.R
import com.example.modern_city.ui.HomeActivity
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

          var redisterinfo:SharedPreferences=getSharedPreferences("userInfo_login",Context.MODE_PRIVATE)
            val editor:SharedPreferences.Editor=redisterinfo.edit()



            val retrofit=Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api:API=retrofit.create(API::class.java)

            val call=api.register(1,edt_register_Fname.text.toString().trim()
                ,edt_register_Lname.text.toString().trim(),edt_register_email.text.toString().trim()
                ,"mail",edt_register_password.text.toString().trim()
                ,"minia",edt_register_phone.text.toString().trim())

            call.enqueue(object :Callback<UserRegister>{
                override fun onResponse(
                    call: Call<UserRegister>?,
                    response: Response<UserRegister>?)
                {

                    try {
                        Toast.makeText(this@RegisterActivity,response?.body()?.data?.phone.toString(),Toast.LENGTH_LONG).show()

                    }catch (ex:Exception){
                        Toast.makeText(this@RegisterActivity,ex.message,Toast.LENGTH_LONG).show()

                    }
                      editor.apply{
                          putString("userName",response?.body()?.data?.first_name)
                          putString("email",response?.body()?.data?.email)
                          putString("gender",response?.body()?.data?.gender)
                          putString("token",response?.body()?.data?.token)
                          putString("phone",response?.body()?.data?.phone.toString())
                          putString("adress",response?.body()?.data?.address)
                          response?.body()?.data?.user_id?.let { it1 -> putInt("city_id", it1) }
                          response?.body()?.data?.user_id?.let { it1 -> putInt("user_id", it1) }
                          response?.body()?.data?.user_group_id?.let { it1 -> putInt("user_group__id", it1) }
                      }.commit()


                    val intenthome=Intent(this@RegisterActivity,HomeActivity::class.java)
                    startActivity(intenthome)

                }

                override fun onFailure(call: Call<UserRegister>?, t: Throwable?) {
                    Toast.makeText(this@RegisterActivity,"Failer",Toast.LENGTH_LONG).show()

                }

            })








        }
    }


}