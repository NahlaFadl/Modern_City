package com.example.modern_city.Models.adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.API_SERVIECS.CrafsTypes_Response
import com.example.modern_city.R
import com.example.modern_city.ui.ListOfCrafts
import com.example.modern_city.ui.profiles.CraftsmanProfilActivity


class Adapter_rcy_craftsTypes(var crafsType:List<CrafsTypes_Response>)
    : RecyclerView.Adapter<Adapter_rcy_craftsTypes.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context)
            .inflate(R.layout.rcyitem_mainservices,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = crafsType[position]
        holder.textView.text= ItemsViewModel.All_Craftsman_Types[position].craftsman_type_name

        holder.itemView.setOnClickListener {

            val context=holder.textView.context

            var intent= Intent(context, ListOfCrafts::class.java)
            intent.putExtra("craftsman_type_id",ItemsViewModel.All_Craftsman_Types[position].craftsman_type_id)
          //  intent.putExtra("crafs_type_name",ItemsViewModel.All_Craftsman_Types[position].craftsman_type_name)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {

        return crafsType.size
    }



    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.img_station)
        val textView: TextView = itemView.findViewById(R.id.txt_station)
    }
}