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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craftsman_register)
        planets_spinner

        loadcrafstype()
        loaddata()

        val spinerList = arrayOf("كهربائي ", "مكانيكي", "نجار", "سباك")



        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, spinerList)
        planets_spinner.adapter = adapter


//        val spinner: Spinner = findViewById(R.id.planets_spinner)
//    // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.planets_array,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinner.adapter = adapter
//        }
    }

    fun loadcrafstype(){
        val call= ApiClient.instance?.getMyApi()?.spinerList_crafs()
        if (call != null) {
            call.enqueue(object :Callback<List<Spiner_list_responce>>{
                override fun onResponse(
                    call: Call<List<Spiner_list_responce>>?,
                    response: Response<List<Spiner_list_responce>>?
                ) {

                  //  Log.v("ddd",placeArray.toString())
                }

                override fun onFailure(call: Call<List<Spiner_list_responce>>?, t: Throwable?) {
                    TODO("Not yet implemented")
                }
            })


        }
    }



  fun loaddata(){

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
                      var intr=Intent(this@CraftsmanRegisterActivity,CraftsmanProfilActivity::class.java)
                        startActivity(intr)
                    }

                    override fun onFailure(call: Call<Crafs_Register_Responces>?, t: Throwable?) {

                    }
                })
            }










        }




    }

}