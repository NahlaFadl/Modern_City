package com.example.modern_city.ui.profiles

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.Crafs_Details_Responce
import com.example.modern_city.API_SERVIECS.DetailsOfCraftsman
import com.example.modern_city.Models.adapters.Adapter_rcy_craftsTypes
import com.example.modern_city.R
import kotlinx.android.synthetic.main.activity_catagories.*
import kotlinx.android.synthetic.main.activity_craftsman_profil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CraftsmanProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craftsman_profil)

        var id= intent.extras?.getString("craftsman_id")
        val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
        var token=  sharedPreferences.getString("token",null)

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
}


