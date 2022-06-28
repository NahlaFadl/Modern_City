package com.example.modern_city.ui.Stations_types

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.ListOfCrafs_Response
import com.example.modern_city.API_SERVIECS.StationRoutsResponce
import com.example.modern_city.Models.adapters.Adapter_StationRouts
import com.example.modern_city.Models.adapters.Adapter_listOfCrafs
import com.example.modern_city.R
import kotlinx.android.synthetic.main.activity_list_of_crafts.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Station_Routs : AppCompatActivity() {

    lateinit var rcy_Station_Rout:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_station_routs)
        val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
        var token=  sharedPreferences.getString("token",null)
        var id:Int= intent.extras?.get("bus_route_id") as Int
        rcy_Station_Rout=findViewById(R.id.rcy_Station_Rout)

    try{
        if (token != null){

            loadRout(token,id)



        }else{
            Toast.makeText(this@Station_Routs,"tokent =0", Toast.LENGTH_LONG).show()
        }


    }catch (e:Exception)
    {
        throw  e
    }
}
    fun loadRout(token :String,id:Int){


        val call= ApiClient.instance?.getMyApi()?.getStationRoutes(token,id)

        if (call!=null){
            call.enqueue(object :Callback<StationRoutsResponce>{
                override fun onResponse(
                    call: Call<StationRoutsResponce>?,
                    response: Response<StationRoutsResponce>?
                ) {




                    var StationArray:ArrayList<StationRoutsResponce> = ArrayList()
                    for(i in 1..20){
//
//                       crafsArray=( response?.body()?.bus_routes_station?.bus_route_stations)
//
//                        val layoutManager: RecyclerView.LayoutManager = StaggeredGridLayoutManager(
//                            1,
//                            StaggeredGridLayoutManager.VERTICAL
//                        )
//                        rcy_Station_Rout.setLayoutManager(layoutManager)
//                        rcy_Station_Rout.setItemAnimator(DefaultItemAnimator())
//                        val adapter = Adapter_StationRouts(crafsArray!!)
//                        rcy_Station_Rout.adapter = adapter
                    }




                }

                override fun onFailure(call: Call<StationRoutsResponce>?, t: Throwable?) {
                    TODO("Not yet implemented")
                }
            })

        }
    }

    }


