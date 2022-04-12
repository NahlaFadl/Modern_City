package com.example.modern_city.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.Adapter_rcy_mainService
import com.example.modern_city.Adapter_rcy_mostFamiller
import com.example.modern_city.Adapter_slider
import com.example.modern_city.Models.model_home_main_service
import com.example.modern_city.Models.model_home_mostFamalier
import com.example.modern_city.R
import com.smarteist.autoimageslider.SliderView


class HomeFagment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View
        view=inflater.inflate(R.layout.fragment_home_fagment, container, false)

        val imageSlider =view.findViewById<SliderView>(R.id.imageSlider)
        val imageList: ArrayList<String> = ArrayList()
        imageList.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg")
        imageList.add("https://images.ctfassets.net/hrltx12pl8hq/4plHDVeTkWuFMihxQnzBSb/aea2f06d675c3d710d095306e377382f/shutterstock_554314555_copy.jpg")
        imageList.add("https://media.istockphoto.com/photos/child-hands-formig-heart-shape-picture-id951945718?k=6&m=951945718&s=612x612&w=0&h=ih-N7RytxrTfhDyvyTQCA5q5xKoJToKSYgdsJ_mHrv0=")
        setImageInSlider(imageList, imageSlider)




        /////////////////////////////
        // getting the recyclerview by its id
        val rcy_mainservice =view.findViewById<RecyclerView>(R.id.rcy_mainService)

//        // this creates a vertical layout Manager
//        rcy_mainservice.layoutManager = LinearLayoutManager(activity)
        rcy_mainservice.apply {
            layoutManager = GridLayoutManager(activity, 2)
        }

        // ArrayList of class ItemsViewModel
        val data_mainservice = ArrayList<model_home_main_service>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data_mainservice.add(model_home_main_service(R.drawable.ic_favorite,"jj"))

        }

        // This will pass the ArrayList to our Adapter
        val adapter_mainservice = Adapter_rcy_mainService(data_mainservice)

        // Setting the Adapter with the recyclerview
        rcy_mainservice.adapter = adapter_mainservice


////////////////////////////////////

        val recyclerview =view.findViewById<RecyclerView>(R.id.rcy_mostFamilyer)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(activity,LinearLayoutManager
            .HORIZONTAL, false)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<model_home_mostFamalier>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(model_home_mostFamalier(R.drawable.dog))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = Adapter_rcy_mostFamiller(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter


////////////////////////////////



        return view
    }




    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = Adapter_slider()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = true
        imageSlider.startAutoCycle()
    }





}