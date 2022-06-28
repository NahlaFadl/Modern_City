package com.example.modern_city.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.*
import com.example.modern_city.API_SERVIECS.Adds_Responces
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.DetailsUserResponse
import com.example.modern_city.API_SERVIECS.FamousPlacesResponse
import com.example.modern_city.Models.*
import com.example.modern_city.Models.adapters.Adapter_rcy_mainService
import com.example.modern_city.Models.adapters.Adapter_rcy_mostFamiller
import com.example.modern_city.Models.adapters.Adapter_slider
import com.example.modern_city.ui.Stations_types.StationsTypes
import com.example.modern_city.ui.categories.CrafsType
import com.example.modern_city.ui.profiles.ProfileActivity
import com.example.modern_city.ui.search.SearchActivity
import com.smarteist.autoimageslider.SliderView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home_fagment.*
import kotlinx.android.synthetic.main.fragment_home_fagment.user_imageProf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFagment : Fragment() {
    //inti Recycler famous places
    lateinit var recyclerFamousPlaces:RecyclerView
    // inti Recycler Main Service
    lateinit var rcy_mainservice:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View
        view=inflater.inflate(R.layout.fragment_home_fagment, container, false)

        var tx_crafs =view.findViewById<TextView>(R.id.txt_crafs)

        tx_crafs.setOnClickListener {
            val intto:Intent= Intent(activity,CrafsType::class.java)
            intto.putExtra("categoryName","خدمة الصنايعي")
            startActivity(intto)
        }
        var HomeSearch =view.findViewById<TextView>(R.id.edt_HomeSearch)

        HomeSearch.setOnClickListener {
            val intto:Intent= Intent(activity,SearchActivity::class.java)

            startActivity(intto)
        }



////////go to station
        var tx_station_home =view.findViewById<TextView>(R.id.txt_station_home)

        tx_station_home.setOnClickListener {
            val intto:Intent= Intent(activity,StationsTypes::class.java)
            intto.putExtra("categoryName","خدمة المواصلات")
            startActivity(intto)
        }


        val sharedPreferences = requireActivity().applicationContext
            .getSharedPreferences("userInfo_login",Context.MODE_PRIVATE)
////////\adds
        loadAdds(sharedPreferences.getString("token",null).toString())



       Toast.makeText(activity,sharedPreferences.getString("email",null),Toast.LENGTH_LONG).show()
        Log.d("gerges",sharedPreferences.getString("email",null).toString())


//////////////////////////////////////////////////
        /** famous places */
         recyclerFamousPlaces=view.findViewById<RecyclerView>(R.id.rcy_famousPlaces)
        recyclerFamousPlaces.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
        famousPlaces(sharedPreferences.getString("token",null).toString())

        /** main service */
         rcy_mainservice =view.findViewById<RecyclerView>(R.id.rcy_mainService)
        // this creates a vertical layout Manager
        rcy_mainservice.apply {
            layoutManager = GridLayoutManager(activity, 2) }
        mainServices(sharedPreferences.getString("token",null).toString())

        /** getUserProfile */
        getUserProfile(sharedPreferences.getString("token",null).toString(),sharedPreferences.getInt("user_id",0))
        return view
    }

    fun getUserProfile(Token:String,ID:Int){
        var call= ApiClient.instance?.getMyApi()?.showUserDetails(Token,ID)

        if (call != null){

            call.enqueue(object : Callback<DetailsUserResponse> {
                override fun onResponse(
                    call: Call<DetailsUserResponse>?,
                    response: Response<DetailsUserResponse>?
                ) {

                    Toast.makeText(activity,response?.body()?.details_of_user?.email, Toast.LENGTH_LONG).show()

                    Picasso.get().load(response?.body()?.details_of_user?.user_img).into(user_imageProf)

                }

                override fun onFailure(call: Call<DetailsUserResponse>?, t: Throwable?) {
                    Toast.makeText(activity,"Faliar"
                        , Toast.LENGTH_LONG).show()
                }

            } )
        }else
            Toast.makeText(activity,"Faliar222", Toast.LENGTH_LONG).show()
    }

    // function to get famous places
    fun famousPlaces(token:String){
        var call=ApiClient.instance?.getMyApi()
            ?.getFamousPlace(token)


        if (call!=null){

            call.enqueue(object : Callback<FamousPlacesResponse>{
                override fun onResponse(
                    call: Call<FamousPlacesResponse>?,
                    response: Response<FamousPlacesResponse>?
                ) {
                    Toast.makeText(activity,response?.body()?.status.toString()+"\nfffffffffff",Toast.LENGTH_LONG).show()



                    var listSize: Int? = response?.body()?.famous_places?.size
                    var famousPlaceArray: ArrayList<FamousPlacesResponse> = ArrayList()

                    for (i in 1..listSize!!) {
                        famousPlaceArray.add(response?.body()!!)
                    }
                    val adapter = Adapter_rcy_mostFamiller(famousPlaceArray!!)
                    recyclerFamousPlaces.adapter = adapter
                }

                override fun onFailure(call: Call<FamousPlacesResponse>?, t: Throwable?) {
                    TODO("Not yet implemented")
                }
            })

        }
    }

    // function to get main service
    fun mainServices(token:String){
        var call=ApiClient.instance?.getMyApi()
            ?.gitCategory(token)


        if (call!=null){

            call.enqueue(object : Callback<CategoriesRespon>{
                override fun onResponse(
                    call: Call<CategoriesRespon>?,
                    response: Response<CategoriesRespon>?
                ) {
                    Toast.makeText(activity,response?.body()?.msg,Toast.LENGTH_LONG).show()



                    var listSize: Int? = response?.body()?.All_Places_Types?.size
                    var placeArray: ArrayList<CategoriesRespon> = ArrayList()

                    for (i in 1..listSize!!) {
                        placeArray.add(response?.body()!!)
                    }
                    val adapter = Adapter_rcy_mainService(placeArray!!)
                    rcy_mainservice.adapter = adapter
                }

                override fun onFailure(call: Call<CategoriesRespon>?, t: Throwable?) {
                    TODO("Not yet implemented")
                }
            })

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        user_imageProf.setOnClickListener {
            val intent= Intent(activity, ProfileActivity::class.java)
            startActivity(intent)

        }
    }

    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = Adapter_slider()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = true
        imageSlider.startAutoCycle()
    }


    fun loadAdds(token:String){

        var call=ApiClient.instance?.getMyApi()

            ?.gitAdds(token)
        if (call != null) {
            call.enqueue(object :Callback<Adds_Responces>{
                override fun onResponse(

                    call: Call<Adds_Responces>?,
                    response: Response<Adds_Responces>?
                ) {

                    var list:ArrayList<String> =ArrayList()

                    for (i in 1..5){
                        response?.body()?.show_advertisement?.get(i)?.small_img?.let { list.add(it) }



                    setImageInSlider(list,imageSlider)
                        Log.v("fcf", response?.body()?.show_advertisement?.get(i)?.small_img.toString())
                    }
                }

                override fun onFailure(call: Call<Adds_Responces>?, t: Throwable?) {
                    TODO("Not yet implemented")
                }
            })
        }
    }




}



