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
import com.example.modern_city.API_SERVIECS.LoginCraftResponse
import com.example.modern_city.R
import com.example.modern_city.ui.profiles.CraftsmanProfilActivity
import kotlinx.android.synthetic.main.activity_login.*
//import kotlinx.android.synthetic.main.activity_login.prog_login2
import kotlinx.android.synthetic.main.activity_login_crafs.*
import kotlinx.android.synthetic.main.activity_regester.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login_Crafs : AppCompatActivity() {

    lateinit var masege:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_crafs)
        supportActionBar?.hide()
        prog_login2.visibility = View.INVISIBLE


        //to login
        lay_crafsLogin.setOnClickListener {
            prog_login2.visibility = View.INVISIBLE
            constraint_loginCraft.visibility = View.VISIBLE

            var email=edt_login_crafsname.text.toString().trim()
            var pass=edt_login_crafsPassword.text.toString().trim()
            edt_login_crafsPassword.inputType = InputType.TYPE_CLASS_NUMBER


            if (!email.isEmpty()&&!pass.isEmpty()){
                login(email,pass)
            }
            else{
                Toast.makeText(this@Login_Crafs,"ادخل جميع البيانات",Toast.LENGTH_LONG).show()
                constraint_login.visibility = View.VISIBLE
                prog_login2.visibility = View.INVISIBLE

            }
        }


        txt_toRegistercrafs.setOnClickListener {
            val intent= Intent(this@Login_Crafs,CraftsmanRegisterActivity::class.java)
            startActivity(intent)
        }
    }





    fun login(email:String,password:String){
        var redisterinfo: SharedPreferences =getSharedPreferences("crafs_userinf", Context.MODE_PRIVATE)
        val editor =redisterinfo.edit()

        var call= ApiClient.instance?.getMyApi()?.craftLogin(email,password,"crafts")


        if (call != null){
            call.enqueue(object : Callback<LoginCraftResponse> {
                override fun onResponse(
                    call: Call<LoginCraftResponse>?,
                    response: Response<LoginCraftResponse>?
                ) {
                    cons2.visibility = View.INVISIBLE
                    prog_login2.visibility = View.VISIBLE
                    if (response!=null){
                        masege=response?.body()?.msg.toString()


                        editor.apply{
                            putString("userName",response?.body()?.Craftsman?.first_name)
                            putString("email",response?.body()?.Craftsman?.email)
                            putString("gender",response?.body()?.Craftsman?.gender)
                            putString("token",response?.body()?.Craftsman?.token)
                            putString("phone",response?.body()?.Craftsman?.phone.toString())
                            putString("adress",response?.body()?.Craftsman?.address)
                            putString("password",edt_login_crafsPassword.text.toString())
                            response?.body()?.Craftsman?.craftsman_id?.let { it1 -> putInt("city_id", it1) }
                            response?.body()?.Craftsman?.craftsman_id?.let { it1 -> putInt("user_id", it1) }
                            response?.body()?.Craftsman?.craftsman_type_id?.let { it1 -> putInt("user_group__id", it1)}

                        }.commit()
                        Toast.makeText(this@Login_Crafs,response?.body()?.Craftsman?.email, Toast.LENGTH_LONG).show()
                        val intent= Intent(this@Login_Crafs, CraftsmanProfilActivity::class.java)
                        startActivity(intent)


                    }else{
                        Toast.makeText(this@Login_Crafs,masege, Toast.LENGTH_LONG).show()


                    }
                }

                override fun onFailure(call: Call<LoginCraftResponse>?, t: Throwable?) {
                    Toast.makeText(this@Login_Crafs,"فشل الاتصال حاول مره اخري"
                        , Toast.LENGTH_LONG).show()
                    constraint_login.visibility = View.VISIBLE
                    prog_login2.visibility = View.INVISIBLE
                }

            } )
        }else
            Toast.makeText(this@Login_Crafs,"الطلب غير متاح الان"
                , Toast.LENGTH_LONG).show()
        constraint_loginCraft.visibility = View.VISIBLE
        prog_login2.visibility = View.INVISIBLE
    }
}