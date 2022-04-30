package com.example.modern_city.ui.categories

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.PlaceDetailsResponse
import com.example.modern_city.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_details)
        supportActionBar?.hide()

        var id:Int= intent.extras?.get("place_id") as Int
        val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
        var token=sharedPreferences.getString("token",null).toString()
        if (id!=null||token!=null){

            loadData(token,id)
        }
    }


    fun loadData(token:String,place_id:Int){

        val call=ApiClient.instance?.getMyApi()?.getPlaceDetails(token,place_id)
        if (call!=null) {

            call?.enqueue(object : Callback<PlaceDetailsResponse> {
                override fun onResponse(
                    call: Call<PlaceDetailsResponse>?,
                    response: Response<PlaceDetailsResponse>?
                ) {

                }

                override fun onFailure(call: Call<PlaceDetailsResponse>?, t: Throwable?) {

                }
            })

        }
        else{
            Toast.makeText(this@CategoryDetailsActivity,"call eqal null",Toast.LENGTH_LONG).show()

        }
    }
}