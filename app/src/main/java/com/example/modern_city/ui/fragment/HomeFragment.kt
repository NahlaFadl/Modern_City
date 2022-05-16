package com.example.modern_city.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.*
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.FamousPlacesResponse
import com.example.modern_city.Models.*
import com.example.modern_city.Models.adapters.Adapter_rcy_mainService
import com.example.modern_city.Models.adapters.Adapter_rcy_mostFamiller
import com.example.modern_city.Models.adapters.Adapter_slider
import com.example.modern_city.ui.categories.Categories
import com.example.modern_city.ui.profiles.ProfileActivity
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_home_fagment.*
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
            val intto:Intent= Intent(activity,Categories::class.java)
            startActivity(intto)
        }


        val sharedPreferences = requireActivity().applicationContext
            .getSharedPreferences("userInfo_login",Context.MODE_PRIVATE)

       Toast.makeText(activity,sharedPreferences.getString("email",null),Toast.LENGTH_LONG).show()
        Log.d("gerges",sharedPreferences.getString("email",null).toString())
       // var redisterinfo:SharedPreferences= activity!!.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
//        val editor:SharedPreferences.Editor=redisterinfo.edit()




        val imageSlider =view.findViewById<SliderView>(R.id.imageSlider)
        val imageList: ArrayList<String> = ArrayList()
        imageList.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg")
        imageList.add("https://images.ctfassets.net/hrltx12pl8hq/4plHDVeTkWuFMihxQnzBSb/aea2f06d675c3d710d095306e377382f/shutterstock_554314555_copy.jpg")
        imageList.add("https://media.istockphoto.com/photos/child-hands-formig-heart-shape-picture-id951945718?k=6&m=951945718&s=612x612&w=0&h=ih-N7RytxrTfhDyvyTQCA5q5xKoJToKSYgdsJ_mHrv0=")
        setImageInSlider(imageList, imageSlider)

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

        return view
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


    fun loaddata(){

        
    }




}



