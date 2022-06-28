package com.example.modern_city.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
    lateinit var masege:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regester)

        addnewuser()
        arr_backLogin.setOnClickListener {
            val intentlogin=Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intentlogin)
        }
    }

 fun addnewuser(){
     lay_registerComponent.setOnClickListener {
         consRegister.visibility = View.INVISIBLE
         prog_register.visibility = View.VISIBLE

         // to create sharedPerferences
         var redisterinfo:SharedPreferences=getSharedPreferences("userInfo_login",Context.MODE_PRIVATE)
         val editor:SharedPreferences.Editor=redisterinfo.edit()

         val retrofit=Retrofit.Builder().baseUrl(url)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
         val api:API=retrofit.create(API::class.java)
         var fname=edt_register_Fname.text.toString().trim()
         var lname=edt_register_Lname.text.toString().trim()
         var email=edt_register_email.text.toString().trim()
         var pass=edt_register_password.text.toString().trim()
         var passconf=edt_register_comnfpass.text.toString().trim()
         var phone=edt_register_phone.text.toString().trim()



         if (!fname.isEmpty()&&!lname.isEmpty()&&!email.isEmpty()&&!pass.isEmpty()&&!phone.isEmpty()
             &&!passconf.isEmpty()){

             if (pass.equals(passconf)){

                 val call=api.register(1,fname,lname,email,"mail",pass,"minia",phone)

                 call.enqueue(object :Callback<UserRegister>{
                     override fun onResponse(
                         call: Call<UserRegister>?,
                         response: Response<UserRegister>?)
                     {
                        if (response!=null){
                         masege=response?.body()?.msg.toString()

                         //shard prefrance data
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
                            prog_register.visibility = View.INVISIBLE
                            consRegister.visibility = View.VISIBLE

                     }else{
                            Toast.makeText(this@RegisterActivity,masege,Toast.LENGTH_LONG).show()


                        }
                     }

                     override fun onFailure(call: Call<UserRegister>?, t: Throwable?) {
                         Toast.makeText(this@RegisterActivity,"Failer",Toast.LENGTH_LONG).show()


                     }

                 })


             }else{
                 Toast.makeText(this@RegisterActivity,"كلمت السر غير متتطابقة",Toast.LENGTH_LONG).show()
                 consRegister.visibility = View.VISIBLE
                 prog_register.visibility = View.INVISIBLE

             }

         }

         else{
             Toast.makeText(this@RegisterActivity,"لم يتم ادخال بعض البيانات",Toast.LENGTH_LONG).show()
             consRegister.visibility = View.VISIBLE
             prog_register.visibility = View.INVISIBLE
         }
     }
 }
}



