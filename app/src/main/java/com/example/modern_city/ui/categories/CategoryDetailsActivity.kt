package com.example.modern_city.ui.categories

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.AddFavourite
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.DeleteFavourite
import com.example.modern_city.API_SERVIECS.PlaceDetailsResponse
import com.example.modern_city.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_category_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsActivity : AppCompatActivity() {
    var c:Int?=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_details)
        supportActionBar?.hide()
        var photo:String= intent?.extras?.get("Photo") as String
        Picasso.get().load(photo).into(ReataurateImage)
        var phone:String= intent.extras?.get("phone") as String
        category_phone.text=phone
        var id:Int= intent.extras?.get("place_id") as Int
        val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
        var token=sharedPreferences.getString("token",null).toString()
        if (id!=null||token!=null){

            Toast.makeText(this,"id=="+id.toShort()+"\n token=="+token,Toast.LENGTH_SHORT).show()
            loadData(token,id)

            favorite_categories.setOnClickListener {


                if (c==0){
                    addFavorite(token,id)
                    c=1
                }
                else if(c==1){
                    deletedFavorite(token,id)
                    c=0
                }
            }

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

    // function add favourite
    fun addFavorite(token:String,place_id:Int){

      //  favorite_categories.setOnClickListener {
            val call=ApiClient.instance?.getMyApi()?.addFavourites(token,place_id)
            if (call!=null) {

                call?.enqueue(object : Callback<AddFavourite> {
                    override fun onResponse(
                        call: Call<AddFavourite>?,
                        response: Response<AddFavourite>?
                    ) {

                        Toast.makeText(this@CategoryDetailsActivity,response?.body()?.msg,Toast.LENGTH_SHORT).show()

                    }

                    override fun onFailure(call: Call<AddFavourite>?, t: Throwable?) {

                    }
                })

            }
            else{
                Toast.makeText(this@CategoryDetailsActivity,"call eqal null",Toast.LENGTH_LONG).show()

            }

        //}

    }

    // delete favourite
    fun deletedFavorite(token:String,place_id:Int){

      //  favorite_categories.setOnClickListener {
            val call=ApiClient.instance?.getMyApi()?.deleteFavourites(token,place_id)
            if (call!=null) {

                call?.enqueue(object : Callback<DeleteFavourite> {
                    override fun onResponse(
                        call: Call<DeleteFavourite>?,
                        response: Response<DeleteFavourite>?
                    ) {

                        Toast.makeText(this@CategoryDetailsActivity,response?.body()?.msg,Toast.LENGTH_SHORT).show()

                    }

                    override fun onFailure(call: Call<DeleteFavourite>?, t: Throwable?) {

                    }
                })

            }
            else{
                Toast.makeText(this@CategoryDetailsActivity,"call eqal null",Toast.LENGTH_LONG).show()

            }

        //}

    }
}