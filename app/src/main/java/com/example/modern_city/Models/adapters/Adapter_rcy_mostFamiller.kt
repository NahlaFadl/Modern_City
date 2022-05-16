package com.example.modern_city.Models.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.API_SERVIECS.FamousPlacesResponse
import com.example.modern_city.API_SERVIECS.LoginRespons
import com.example.modern_city.R
import com.example.modern_city.ui.categories.Categories
import com.squareup.picasso.Picasso

class Adapter_rcy_mostFamiller(private val mList: List<FamousPlacesResponse>)
    : RecyclerView.Adapter<Adapter_rcy_mostFamiller.ViewHolder>() {

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

        val ItemsViewModel = mList[position]
        Picasso.get().load(ItemsViewModel.famous_places[position].small_img).into(holder.imageView)
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



