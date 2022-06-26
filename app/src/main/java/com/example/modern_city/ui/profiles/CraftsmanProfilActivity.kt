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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_catagories.*
import kotlinx.android.synthetic.main.activity_craftsman_profil.*
import kotlinx.android.synthetic.main.activity_user_edit_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CraftsmanProfilActivity : AppCompatActivity() {
    var count:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craftsman_profil)

        var id= intent.extras?.getString("craftsman_id")
        val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
        var token=  sharedPreferences.getString("token",null)

        //to intent to edit craft activty
        txt_CraftEdit_account.setOnClickListener {
            val intent=Intent(this,CraftEditProfile::class.java)
            startActivity(intent)
        }
        //to edit status
        switch1.setOnClickListener {
            if (switch1.isChecked && count==0){
                // main.setBackgroundColor(Color.DKGRAY)
                switch1.setTextColor(Color.WHITE)
                switch1.text="ON"
                count++
                Toast.makeText(this,""+count,Toast.LENGTH_SHORT).show()
            }
            else{
                //main.setBackgroundColor(Color.WHITE)
                switch1.setTextColor(Color.BLACK)
                switch1.text="OFF"
                if (count==1)
                {
                    count--
                    Toast.makeText(this,""+count,Toast.LENGTH_SHORT).show()
                }
            }
        }

        craftEditStatus(token.toString(),count)
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
}



