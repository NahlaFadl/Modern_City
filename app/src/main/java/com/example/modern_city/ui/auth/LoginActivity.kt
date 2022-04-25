package com.example.modern_city.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.Models.Loginresponse
import com.example.modern_city.R
import com.example.modern_city.ui.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        txt_login.setOnClickListener {
            var email=edt_login_username.text.toString().trim()
            var pass=edt_login_userPassword.text.toString().trim()

            login(email,pass)
        }

//        txt_register.setOnClickListener {
//            val intent= Intent(this,RegisterActivity::class.java)
//            startActivity(intent)
//        }


    }

    fun login(email:String,password:String){
        var redisterinfo: SharedPreferences =getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
        val editor =redisterinfo.edit()

        var call= ApiClient.instance?.getMyApi()?.login(email,password)

        if (call != null){

            call.enqueue(object :Callback<Loginresponse>{
                override fun onResponse(
                    call: Call<Loginresponse>?,
                    response: Response<Loginresponse>?
                ) {

                   editor.apply{

                       putString("userName",response?.body()?.data?.first_name)
                       putString("email",response?.body()?.data?.email)
                       putString("gender",response?.body()?.data?.gender)
                       putString("token",response?.body()?.data?.token)
                       putString("phone",response?.body()?.data?.phone.toString())
                       putString("adress",response?.body()?.data?.address)
                       response?.body()?.data?.user_id?.let { it1 -> putInt("city_id", it1) }
                       response?.body()?.data?.user_id?.let { it1 -> putInt("user_id", it1) }
                       response?.body()?.data?.user_group_id?.let { it1 -> putInt("user_group__id", it1)}

                   }

                    Toast.makeText(this@LoginActivity,response?.body()?.data?.email
                    ,Toast.LENGTH_LONG).show()

                    val intent= Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)


                }

                override fun onFailure(call: Call<Loginresponse>?, t: Throwable?) {
                    Toast.makeText(this@LoginActivity,"Faliar"
                        ,Toast.LENGTH_LONG).show()
                }

            } )



        }else
            Toast.makeText(this@LoginActivity,"Faliar222"
                ,Toast.LENGTH_LONG).show()




    }


}





