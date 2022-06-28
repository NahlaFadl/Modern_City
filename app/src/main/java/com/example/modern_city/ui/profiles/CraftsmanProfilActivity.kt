package com.example.modern_city.ui.profiles

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.modern_city.API_SERVIECS.*
import com.example.modern_city.Models.adapters.Adapter_rcy_craftsTypes
import com.example.modern_city.R
import com.example.modern_city.ui.auth.LoginActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_catagories.*
import kotlinx.android.synthetic.main.activity_craftsman_profil.*
import kotlinx.android.synthetic.main.activity_user_edit_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.SharedPreferences




class CraftsmanProfilActivity : AppCompatActivity() {
    var count:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craftsman_profil)

        val sharedPreferences = this.getSharedPreferences("crafs_userinf", Context.MODE_PRIVATE)
        var token=  sharedPreferences.getString("token",null)
        var id=  sharedPreferences.getInt("user_id",0)

        //to craft logout
        txt_logout.setOnClickListener {
            craftLogout(token.toString())
        }
        //to intent to edit craft activty
        txt_CraftEdit_account.setOnClickListener {
            val intent=Intent(this,CraftEditProfile::class.java)
            startActivity(intent)
        }
        //to edit status
        switch1.setOnClickListener {
            if (switch1.isChecked){
                // main.setBackgroundColor(Color.DKGRAY)
                val editor = getSharedPreferences("crafs_userinf", MODE_PRIVATE).edit()
                editor.putBoolean("NameOfThingToSave", true)
                editor.commit()
                craftEditStatus(token.toString(),1)
                switch1.setTextColor(Color.WHITE)
                craftStatus.text="متاح"
                Toast.makeText(this,"1",Toast.LENGTH_SHORT).show()
            }
            else{
                //main.setBackgroundColor(Color.WHITE)
                val editor = getSharedPreferences("crafs_userinf", MODE_PRIVATE).edit()
                editor.putBoolean("NameOfThingToSave", false)
                editor.commit()
                switch1.setTextColor(Color.BLACK)
                craftEditStatus(token.toString(),0)
                craftStatus.text="غير متاح"
                Toast.makeText(this,"0",Toast.LENGTH_SHORT).show()

            }
        }

        // api لسه صاصا هيعمله
       // loadData(token.toString(),id!!)

    }

    fun loadData(token:String,id:Int){
        try {
          val call=ApiClient.instance?.getMyApi()?.getCrafsDetails(token,id)
            if (call!=null){
                call.enqueue(object :Callback<Crafs_Details_Responce>{
                    override fun onResponse(
                        call: Call<Crafs_Details_Responce>?,
                        response: Response<Crafs_Details_Responce>?
                    ) {

             txt_crafsprofile_username.text= response?.body()?.details_of_craftsman?.first_name+" "+
                     response?.body()?.details_of_craftsman?.last_name

           txt_profile_crafs_description.text=response?.body()?.details_of_craftsman?.description.toString()
                    }

                    override fun onFailure(call: Call<Crafs_Details_Responce>?, t: Throwable?) {
                        TODO("Not yet implemented")
                    }
                })

            }else{

                Toast.makeText(this@CraftsmanProfilActivity,"call=null",Toast.LENGTH_LONG).show()
            }

        }catch (e:Exception){
        throw e

        }
    }

    fun craftEditStatus(token:String, stats:Int){
        var call= ApiClient.instance?.getMyApi()?.craftStatus(token,stats)

        if (call != null){

            call.enqueue(object : Callback<CrafStatusResponse> {
                override fun onResponse(
                    call: Call<CrafStatusResponse>?,
                    response: Response<CrafStatusResponse>?
                ) {

                    Toast.makeText(this@CraftsmanProfilActivity,response?.body()?.msg, Toast.LENGTH_LONG).show()

                }

                override fun onFailure(call: Call<CrafStatusResponse>?, t: Throwable?) {
                    Toast.makeText(this@CraftsmanProfilActivity,"Faliar"
                        , Toast.LENGTH_LONG).show()
                }

            } )

        }else
            Toast.makeText(this,"Faliar222", Toast.LENGTH_LONG).show()
    }

    //to craft logout
    fun craftLogout(Token:String){

        var call= ApiClient.instance?.getMyApi()?.craftLogOut(Token)

        if (call != null){

            call.enqueue(object : Callback<CraftLogotResonse> {
                override fun onResponse(
                    call: Call<CraftLogotResonse>?,response: Response<CraftLogotResonse>?) {

                    Toast.makeText(this@CraftsmanProfilActivity,response?.body()?.msg, Toast.LENGTH_LONG).show()

                    val intent = Intent(this@CraftsmanProfilActivity, LoginActivity::class.java)
                    intent.putExtra("finish", true)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // To clean up all activities
                    startActivity(intent)
                    finish()

                }

                override fun onFailure(call: Call<CraftLogotResonse>?, t: Throwable?) {
                    Toast.makeText(this@CraftsmanProfilActivity,"Faliar"
                        , Toast.LENGTH_LONG).show()
                }

            } )

        }else
            Toast.makeText(this,"Faliar222", Toast.LENGTH_LONG).show()
    }

}




