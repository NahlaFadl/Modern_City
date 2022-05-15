package com.example.modern_city.ui.categories

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.ListOfPlaceType
import com.example.modern_city.R
import com.example.modern_city.Models.adapters.RecyclerCHB_Adapter
import kotlinx.android.synthetic.main.activity_categories_chb.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Categories_CHB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_chb)
       loadData()



    }

    fun loadData(){
        val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)

        var token=sharedPreferences.getString("token",null).toString()


        if (token!=null){



            var id:Int= intent.extras?.get("typeId") as Int


            Log.d("id",id.toString())
            var call=ApiClient.instance?.getMyApi()?.gitPlaceByPlaceType(token,id)
            if (call!=null){


                call.enqueue(object:Callback<ListOfPlaceType>{
                    override fun onResponse(
                        call: Call<ListOfPlaceType>?,
                        response: Response<ListOfPlaceType>?
                    ) {
                        Toast.makeText(this@Categories_CHB,response?.body()?.msg.toString(),
                            Toast.LENGTH_LONG).show()
                        var listSize:Int?=response?.body()?.places_by_place_type?.size
                        var placeArray:ArrayList<ListOfPlaceType> = ArrayList()
                        for (i in  1..listSize!!){

                            placeArray.add(response?.body()!!)
                            val adapter = RecyclerCHB_Adapter(placeArray)
                            val layoutManager: RecyclerView.LayoutManager = StaggeredGridLayoutManager(
                                2,
                                StaggeredGridLayoutManager.VERTICAL
                            )
                            recycler_categoriesCHB.setLayoutManager(layoutManager)
                            recycler_categoriesCHB.setItemAnimator(DefaultItemAnimator())
                            recycler_categoriesCHB.setAdapter(adapter)
                        }

                    }

                    override fun onFailure(call: Call<ListOfPlaceType>?, t: Throwable?) {
                        TODO("Not yet implemented")
                    }
                })
            }else
            {
                Toast.makeText(this,"Call equal null",Toast.LENGTH_LONG).show()
            }
        }
        else
        {
            Toast.makeText(this,"token equal null",Toast.LENGTH_LONG).show()
        }
    }
}