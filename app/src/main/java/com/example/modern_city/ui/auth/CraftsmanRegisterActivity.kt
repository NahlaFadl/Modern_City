package com.example.modern_city.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.*
import com.example.modern_city.R
import com.example.modern_city.ui.HomeActivity
import com.example.modern_city.ui.profiles.CraftsmanProfilActivity
import kotlinx.android.synthetic.main.activity_craftsman_register.*
import kotlinx.android.synthetic.main.activity_regester.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class CraftsmanRegisterActivity : AppCompatActivity() {

    val spinerList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craftsman_register)

        loadcrafstype()
        loadDataOfCraftRegister()

        spinerList.add("fff")
        spinerList.add("fff")
        spinerList.add("fff")

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, spinerList)
        planets_spinner.adapter = adapter

    }

    fun loadcrafstype(){
        val call= ApiClient.instance?.getMyApi()?.spinerList_crafs()
        if (call != null) {
            call.enqueue(object :Callback<Spiner_list_responce>{
                override fun onResponse(
                    call: Call<Spiner_list_responce>?,
                    response: Response<Spiner_list_responce>?

                ) {
                    var size=response?.body()?.get_crafts_type?.size
                    var list:ArrayList<String> = ArrayList()
//                    for (i in 1..size!!){
//                  //  var ll= response?.body()?.get_crafts_type?.get(i)?.let { list.add(it.craftsman_type_name) as ArrayList()
//
//
//                        }

                }
                override fun onFailure(call: Call<Spiner_list_responce>?, t: Throwable?) {
                    Toast.makeText(this@CraftsmanRegisterActivity,"فشل الاتصال بالانترنت",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
  fun loadDataOfCraftRegister(){
      registerCraft_txtAct.setOnClickListener {

            var redisterinfo: SharedPreferences =getSharedPreferences("crafs_userinf", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor=redisterinfo.edit()


            val call= ApiClient.instance?.getMyApi()?.registerCrafts(1,2,edt_crafts_rfgistr_Fnam.text.toString().trim()
                ,edt_crafts_rfgistr_Lname.text.toString().trim(),edt_crafts_rfgistr_email.text.toString().trim()
                ,"mail",edt_crafts_rfgistr_password.text.toString().trim()
                ,"minia")
            if (call != null) {
                call.enqueue(object : Callback<Crafs_Register_Responces>{
                    override fun onResponse(
                        call: Call<Crafs_Register_Responces>?,
                        response: Response<Crafs_Register_Responces>?
                    ) {
                        Toast.makeText(this@CraftsmanRegisterActivity,response?.body()?.data?.first_name.toString(),Toast.LENGTH_LONG).show()

                        editor.apply{
                            putString("userName",response?.body()?.data?.first_name)
                            putString("email",response?.body()?.data?.email)
                            putString("gender",response?.body()?.data?.gender)
                            putString("token",response?.body()?.data?.token)
                            putString("phone",response?.body()?.data?.phone.toString())
                            putString("adress",response?.body()?.data?.address)
                            response?.body()?.data?.craftsman_id?.let { it1 -> putInt("city_id", it1) }
                            response?.body()?.data?.craftsman_id?.let { it1 -> putInt("user_id", it1) }
                            response?.body()?.data?.craftsman_id?.let { it1 -> putInt("user_group__id", it1) }
                        }.commit()
                      var intr=Intent(this@CraftsmanRegisterActivity,CraftsmanProfilActivity::class.java)
                        startActivity(intr)
                    }

                    override fun onFailure(call: Call<Crafs_Register_Responces>?, t: Throwable?) {
                        Toast.makeText(this@CraftsmanRegisterActivity,"فشل الاتصال بالانترنت",Toast.LENGTH_SHORT).show()

                    }
                })
            }

        }

    }

}