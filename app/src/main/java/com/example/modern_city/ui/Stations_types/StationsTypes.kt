package com.example.modern_city.ui.Stations_types

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.StationsTypesResponces
import com.example.modern_city.Models.adapters.Adapter_Stations
import com.example.modern_city.R
import kotlinx.android.synthetic.main.activity_list_of_crafts.*
import kotlinx.android.synthetic.main.activity_stations_types.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StationsTypes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stations_types)



        try{
            val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
            var token=  sharedPreferences.getString("token",null)




            if (token != null){

                loadStationName(token)



            }else{
                Toast.makeText(this@StationsTypes,"tokent =0", Toast.LENGTH_LONG).show()
            }


        }catch (e:Exception)
        {

            Log.v("eeeeeee",e.toString())
            Toast.makeText(this@StationsTypes,e.toString(), Toast.LENGTH_LONG).show()
        }

      //  loadStationName("q757zf3bjZ4wPyPSLvCQ2022-06-28 02:16:25Ysg6EVy1Zf0x7mdP2nSGB")
    }

    fun loadStationName(token:String){
        val call= ApiClient.instance?.getMyApi()?.getStation(token)

        if (call!=null){
            call.enqueue(object :Callback<StationsTypesResponces>{
                override fun onResponse(
                    call: Call<StationsTypesResponces>?,
                    response: Response<StationsTypesResponces>?
                )
                {

                    var listsize:Int?=response?.body()?.bus_routes_name?.size
                    var StationArray:ArrayList<StationsTypesResponces> = ArrayList()
                    for(i in 1..listsize!!){

                       // rcy_listofcrafs.visibility= View.VISIBLE
                        StationArray.add(response?.body()!!)
                        val layoutManager: RecyclerView.LayoutManager = StaggeredGridLayoutManager(
                            1,
                            StaggeredGridLayoutManager.VERTICAL
                        )
                        Rcy_Station_Types.setLayoutManager(layoutManager)
                        Rcy_Station_Types.setItemAnimator(DefaultItemAnimator())
                        val adapter = Adapter_Stations(StationArray!!)
                        Rcy_Station_Types.adapter = adapter

                    }



                }

                override fun onFailure(call: Call<StationsTypesResponces>?, t: Throwable?) {
                    TODO("Not yet implemented")
                }
            })
        }



    }


}