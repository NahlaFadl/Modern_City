package com.example.modern_city.Models.adapters

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.with
import com.example.modern_city.API_SERVIECS.FamousPlacesResponse
import com.example.modern_city.API_SERVIECS.PlaceDetailsResponse
import com.example.modern_city.R
import com.squareup.picasso.Picasso
import com.example.modern_city.MainActivity
import com.squareup.picasso.Callback


class Adapter_detailsOfPlace (private val mList: List<String> ,var contex:Context)
    : RecyclerView.Adapter<Adapter_detailsOfPlace.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rcyitem_mostfamiler, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       // val ItemsViewModel = mList[position]
      //  var lis=ItemsViewModel.details_of_place[position].slider_img
//        for (x in mList){


       // Glide.with(contex).load(mList[position]).into(holder.imageView)
          Picasso.get().load(mList[position]).into(holder.imageView)

//        }

      //

        /////
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    //غيرت ال itemview
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_placename)

    }


}


