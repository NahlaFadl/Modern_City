package com.example.modern_city.Models.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.API_SERVIECS.NearestPlaceResponce
import com.example.modern_city.R
import com.example.modern_city.ui.categories.CategoryDetailsActivity
import com.example.modern_city.ui.profiles.DetailsOfCraftActivity

class Adapter_nearstPlace(var nearPlaceLisr:List<NearestPlaceResponce>)

    : RecyclerView.Adapter<Adapter_nearstPlace.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context)
            .inflate(R.layout.rcy_item_nearplace,parent,false)
        return Adapter_nearstPlace.ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = nearPlaceLisr[position]
        var name:String=ItemsViewModel.nearest_place[position].place_name
          holder.texname.text=name
       //holder.txt_placeDistance.text=ItemsViewModel.nearest_place[position].distance.toString()+"متر"
        holder.texname.setOnClickListener {

            val context=holder.texname.context

            var intet= Intent(context, CategoryDetailsActivity::class.java)
            intet.putExtra("place_id",ItemsViewModel.nearest_place[position].place_id)

            context.startActivity(intet)
        }

    }

    override fun getItemCount(): Int {
      return nearPlaceLisr.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var texname :TextView = itemView.findViewById(R.id.txt_nearPlace)
        val txt_placeDistance: TextView =itemView.findViewById(R.id.txt_placeDistance)
}}