package com.example.modern_city.Models.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.API_SERVIECS.StationRoutsResponce
import com.example.modern_city.R

class Adapter_StationRouts(var listOfRouts:List<StationRoutsResponce>)
    : RecyclerView.Adapter<Adapter_StationRouts.viewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_StationRouts.viewHolder {
        val v= LayoutInflater.from(parent.context)
            .inflate(R.layout.rcy_item_stationtype,parent,false)
        return Adapter_StationRouts.viewHolder(v)
    }

    override fun onBindViewHolder(holder: Adapter_StationRouts.viewHolder, position: Int) {
        val ItemsViewModel = listOfRouts[position]


            holder.textName.text=ItemsViewModel.bus_routes_station[position].bus_route_name




    }

    override fun getItemCount(): Int {
       return listOfRouts.size
    }

    class viewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val textName: TextView = itemView.findViewById(R.id.tex_StationName)
    }

}