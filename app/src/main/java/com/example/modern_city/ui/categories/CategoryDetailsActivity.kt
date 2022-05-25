package com.example.modern_city.ui.categories

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.API_SERVIECS.*
import com.example.modern_city.Models.adapters.Adapter_detailsOfPlace
import com.example.modern_city.Models.adapters.Adapter_rcy_mostFamiller
import com.example.modern_city.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_category_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsActivity : AppCompatActivity(), OnMapReadyCallback {
    // favourite check counter
    var c:Int?=0
    // map
    private lateinit var mMap: GoogleMap
     var latitude:Double?=0.0
     var longtuide:Double?=0.0
    private lateinit var placeName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_details)

        placeName = intent.extras?.get("place_name") as String
        try {
            supportActionBar?.hide()
            var photo:String= intent?.extras?.get("Photo") as String
            Picasso.get().load(photo).into(ReataurateImage)
            var phone:String= intent.extras?.get("phone") as String
            category_phone.text=phone
            var id:Int= intent.extras?.get("place_id") as Int

            val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
            var token=sharedPreferences.getString("token",null).toString()

            if (id!=null&&token!=null){

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
        }catch (e:Exception){
            throw e
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }





    fun loadData(token:String,place_id:Int){

        val call=ApiClient.instance?.getMyApi()?.getPlaceDetails(token,place_id)
        if (call!=null) {

            call?.enqueue(object : Callback<PlaceDetailsResponse> {
                override fun onResponse(
                    call: Call<PlaceDetailsResponse>?,
                    response: Response<PlaceDetailsResponse>?
                ) {


               var list: List<String>? =response?.body()?.details_of_place?.slider_img
                    Log.d("rr",response?.body()?.msg.toString())
                   // Toast.makeText(this,response?.body()?.details_of_place?.slider_img.toString(),Toast.LENGTH_SHORT).show()

                  rcy_menuslider.layoutManager = LinearLayoutManager(this@CategoryDetailsActivity,
                       LinearLayoutManager.HORIZONTAL, false)

                   val adapter = list?.let { Adapter_detailsOfPlace(it,this@CategoryDetailsActivity) }
                    rcy_menuslider.adapter = adapter

                    latitude=response?.body()?.details_of_place?.geo_location_lat
                    longtuide=response?.body()?.details_of_place?.geo_location_long
                    placeName= response?.body()?.details_of_place?.place_name.toString()

                    Toast.makeText(this@CategoryDetailsActivity,"pplat:"+latitude+", pplon"+longtuide,Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<PlaceDetailsResponse>?, t: Throwable?) {


                }
            })

        }
        else{
            Toast.makeText(this@CategoryDetailsActivity,"call eqal null",Toast.LENGTH_LONG).show()

        }
    }

    override fun onStart() {
        super.onStart()


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

    // map
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        Toast.makeText(this,"lat:"+latitude+", lon"+longtuide,Toast.LENGTH_SHORT).show()
        // Add a marker in Sydney and move the camera
        val placeLocation = LatLng(latitude!!, longtuide!!)
        mMap.addMarker(MarkerOptions().position(placeLocation).title(""+placeName))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeLocation,10f))
    }
}