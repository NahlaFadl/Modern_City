package com.example.modern_city.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.LoginRespons
import com.example.modern_city.R
import com.example.modern_city.ui.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.lay_loginComponent
import kotlinx.android.synthetic.main.activity_login.prog_login
import kotlinx.android.synthetic.main.activity_login_crafs.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login_Crafs : AppCompatActivity() {

    lateinit var masege:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_crafs)
        supportActionBar?.hide()
        prog_login.visibility = View.INVISIBLE


        //to login
        lay_crafsLogin.setOnClickListener {
            prog_login.visibility = View.INVISIBLE
            constraint_login.visibility = View.VISIBLE

            var email=edt_login_crafsname.text.toString().trim()
            var pass=edt_login_crafsPassword.text.toString().trim()
            edt_login_crafsPassword.inputType = InputType.TYPE_CLASS_NUMBER


            if (!email.isEmpty()&&!pass.isEmpty()){
                login(email,pass)
            }
            else{
                Toast.makeText(this@Login_Crafs,"ادخل جميع البيانات",Toast.LENGTH_LONG).show()
                constraint_login.visibility = View.VISIBLE
                prog_login.visibility = View.INVISIBLE

            }
        }


        txt_toRegistercrafs.setOnClickListener {
            val intent= Intent(this@Login_Crafs,CraftsmanRegisterActivity::class.java)
            startActivity(intent)
        }
    }





    fun login(email:String,password:String){
        var redisterinfo: SharedPreferences =getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
        val editor =redisterinfo.edit()

        var call= ApiClient.instance?.getMyApi()?.login(email,password,"crafts")


        if (call != null){
            call.enqueue(object : Callback<LoginRespons> {
                override fun onResponse(
                    call: Call<LoginRespons>?,
                    response: Response<LoginRespons>?
                ) {
                    if (response!=null){
                        masege=response?.body()?.msg.toString()


                        editor.apply{
                            putString("userName",response?.body()?.User?.first_name)
                            putString("email",response?.body()?.User?.email)
                            putString("gender",response?.body()?.User?.gender)
                            putString("token",response?.body()?.User?.token)
                            putString("phone",response?.body()?.User?.phone.toString())
                            putString("adress",response?.body()?.User?.address)
                            response?.body()?.User?.user_id?.let { it1 -> putInt("city_id", it1) }
                            response?.body()?.User?.user_id?.let { it1 -> putInt("user_id", it1) }
                            response?.body()?.User?.user_group_id?.let { it1 -> putInt("user_group__id", it1)}

                        }.commit()
                        Toast.makeText(this@Login_Crafs,response?.body()?.User?.email, Toast.LENGTH_LONG).show()
                        constraint_login.visibility = View.VISIBLE
                        prog_login.visibility = View.INVISIBLE
                        val intent= Intent(this@Login_Crafs, HomeActivity::class.java)
                        startActivity(intent)


                    }else{
                        Toast.makeText(this@Login_Crafs,masege, Toast.LENGTH_LONG).show()


                    }
                }

                override fun onFailure(call: Call<LoginRespons>?, t: Throwable?) {
                    Toast.makeText(this@Login_Crafs,"فشل الاتصال حاول مره اخري"
                        , Toast.LENGTH_LONG).show()
                    constraint_login.visibility = View.VISIBLE
                    prog_login.visibility = View.INVISIBLE
                }

            } )
        }else
            Toast.makeText(this@Login_Crafs,"الطلب غير متاح الان"
                , Toast.LENGTH_LONG).show()
        constraint_login.visibility = View.VISIBLE
        prog_login.visibility = View.INVISIBLE
    }
}