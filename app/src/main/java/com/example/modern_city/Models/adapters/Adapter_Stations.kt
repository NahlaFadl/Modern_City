package com.example.modern_city.Models.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.API_SERVIECS.StationsTypesResponces
import com.example.modern_city.R
import com.example.modern_city.ui.Stations_types.Station_Routs

class Adapter_Stations(var listOfStation:List<StationsTypesResponces>):
    RecyclerView.Adapter<Adapter_Stations.ViewHolder>()

{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context)
            .inflate(R.layout.rcy_item_stationtype,parent,false)
        return Adapter_Stations.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = listOfStation[position]
        var Name:String=ItemsViewModel.bus_routes_name[position]!!.bus_route_name

        holder.textName.text=Name
        holder.textName.setOnClickListener {
            val context=holder.textName.context

            var intent= Intent(context, Station_Routs::class.java)
            intent.putExtra("bus_route_id",ItemsViewModel.bus_routes_name[position].bus_route_id)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return listOfStation.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val textName: TextView = itemView.findViewById(R.id.tex_StationName)
      //val constraint: TextView = itemView.findViewById(R.id.constraintStation)

    }
}