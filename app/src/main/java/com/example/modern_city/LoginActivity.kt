package com.example.modern_city

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.API
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.Models.Loginresponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        txt_login.setOnClickListener {
            login(edt_login_username.text.toString().trim()
                ,edt_login_userPassword.text.toString().trim())
        }

//        txt_register.setOnClickListener {
//            val intent= Intent(this,RegisterActivity::class.java)
//            startActivity(intent)
//        }


    }

    fun login(email:String,password:String){

        var call= ApiClient.instance?.getMyApi()?.login(email,password)

        if (call != null){

            call.enqueue(object :Callback<Loginresponse>{
                override fun onResponse(
                    call: Call<Loginresponse>?,
                    response: Response<Loginresponse>?
                ) {

                    Toast.makeText(this@LoginActivity,response?.body()?.user?.email
                    ,Toast.LENGTH_LONG).show()
                    val intent= Intent(this@LoginActivity,HomeActivity::class.java)
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





