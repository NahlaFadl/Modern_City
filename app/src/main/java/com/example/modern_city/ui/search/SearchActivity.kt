package com.example.modern_city.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.media.audiofx.Equalizer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.NearestPlaceResponce
import com.example.modern_city.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Response
import java.util.jar.Manifest
import javax.security.auth.callback.Callback

class SearchActivity : AppCompatActivity() {
    var count:Int=0

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var tvLati:String
    lateinit var tvLong:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

/////////////////////////
        getLocation()
        /////////////////


        var arr= arrayOf("مطاعم","مصالح حكومية","بنوك","مستشفيات")

        edt_SearchText.setAdapter(
            ArrayAdapter<String>(
                this@SearchActivity,

                R.layout.support_simple_spinner_dropdown_item,
                arr
            )
        )
        ////// ابقي شوفي الايدهات وحطيها
        btn_search.setOnClickListener {
            var x:String =edt_SearchText.text.toString()
            if(x.equals("مطاعم")){
                count=1

            } else if (x.equals("مصالح حكومية"))
            {
                count=2
            }
            else if (x.equals("بنوك"))
            {
                count=3
            }
            Log.v("count",count.toString())
            val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
            var token=sharedPreferences.getString("token",null).toString()
            //ف///خلي بالك انا مخزن خط الطول والعرض في استرنج فممكن يسبب ايرور لو كده جربي ال دبل وغيرها برضوا في api
            LoadNerstLocation(token,count,tvLati,tvLong)
        }






        getLocation()
    }

    fun getLocation(){
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)

        getCurentLocation()


    }
    /////////////////////////////////////////
   fun LoadNerstLocation(token:String,place_id:Int,late:String,longe:String){
        val call=ApiClient.instance?.getMyApi()?.getNearstPlace(token,place_id,late,longe)
         if (call!=null){
             call.enqueue(object : retrofit2.Callback<NearestPlaceResponce>{
                 override fun onResponse(
                     call: Call<NearestPlaceResponce>?,
                     response: Response<NearestPlaceResponce>?
                 ) {
                     Toast.makeText(this@SearchActivity, response?.body()?.msg, Toast.LENGTH_SHORT).show()
                 }

                 override fun onFailure(call: Call<NearestPlaceResponce>?, t: Throwable?) {
                     TODO("Not yet implemented")
                 }
             })


         }



   }










    //////////////////////////////////////////////////////////////// find location

    fun getCurentLocation(){

        if (checkPermition()){
            if (isLocationEnabeld()){

                //lat
                // long
                if (ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this){ task->
                    val locatin:Location?=task.result
                    if(locatin==null){

                        Toast.makeText(this@SearchActivity,"موقعك الحالي بصفر",Toast.LENGTH_LONG).show()

                    }else

                    {
                        Toast.makeText(this@SearchActivity,"موقعك الحالي صحيح",Toast.LENGTH_LONG).show()
                      tvLati=locatin.latitude.toString()
                        tvLong=locatin.longitude.toString()

                        Toast.makeText(this@SearchActivity,tvLong,Toast.LENGTH_LONG).show()
                        Log.v("rsr",tvLati)

                    }


                }


            }else {
                ////seting
                Toast.makeText(this@SearchActivity,"قم بتفعيل الموقع",Toast.LENGTH_LONG).show()
                val itentSettig=Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(itentSettig)

            }



        }
        else {
            ///rqestpermition
            requestPermition()

        }



    }
    fun requestPermition(){
        ActivityCompat.requestPermissions(this,
        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION ),
        PERMISSION_REQUEST_ACCESS_LOCATION)

    }

    companion object{
        private const val PERMISSION_REQUEST_ACCESS_LOCATION=100


    }

    //////////
    fun isLocationEnabeld():Boolean{

     val locationManager:LocationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)




    }




    fun checkPermition():Boolean{

        if (ActivityCompat.checkSelfPermission(
                this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(this
                ,android.Manifest.permission.ACCESS_FINE_LOCATION)==
            PackageManager.PERMISSION_GRANTED){
                return true
        }
        return false

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode== PERMISSION_REQUEST_ACCESS_LOCATION){
            if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this@SearchActivity,"granted",Toast.LENGTH_SHORT)
                    .show()
                getCurentLocation()
            }else{
                Toast.makeText(this@SearchActivity,"denaid",Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }
}