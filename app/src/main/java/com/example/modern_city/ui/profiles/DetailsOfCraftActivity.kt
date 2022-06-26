package com.example.modern_city.ui.profiles

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.Crafs_Details_Responce
import com.example.modern_city.API_SERVIECS.DetailsUserResponse
import com.example.modern_city.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_craftsman_profil.*
import kotlinx.android.synthetic.main.activity_craftsman_profil.txt_crafsprofile_username
import kotlinx.android.synthetic.main.activity_details_of_craft.*
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsOfCraftActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_of_craft)

        var craftsman_id: Int? = intent?.extras?.getInt("craftsman_id")
        val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
        var token=sharedPreferences.getString("token",null).toString()

        loadData(token.toString(),craftsman_id!!)
    }

    //to show profile of craft to user
    fun loadData(token:String,id:Int){
        try {
            val call=ApiClient.instance?.getMyApi()?.getCrafsDetails(token,id)
            if (call!=null){
                call.enqueue(object :Callback<Crafs_Details_Responce>{
                    override fun onResponse(
                        call: Call<Crafs_Details_Responce>?,
                        response: Response<Crafs_Details_Responce>?
                    ) {

                        //to load user profile photo
                        Picasso.get().load(response?.body()?.details_of_craftsman?.craftsman_img).into(imageProfileCraftDet)

                        var craftNaem=response?.body()?.details_of_craftsman?.first_name+" "+
                                response?.body()?.details_of_craftsman?.last_name
                        txt_crafsprofile_usernameDet.text=craftNaem
                        txt_profile_craft_descriptionDet.text=response?.body()?.details_of_craftsman?.description.toString()
                    }

                    override fun onFailure(call: Call<Crafs_Details_Responce>?, t: Throwable?) {
                        Toast.makeText(this@DetailsOfCraftActivity,"not connect",Toast.LENGTH_LONG).show()
                    }
                })

            }else{

                Toast.makeText(this,"call=null",Toast.LENGTH_LONG).show()
            }

        }catch (e:Exception){
            throw e

        }
    }

}