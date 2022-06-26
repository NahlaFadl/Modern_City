package com.example.modern_city.Models.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.API_SERVIECS.ListOfCrafs_Response
import com.example.modern_city.R
import com.example.modern_city.ui.profiles.DetailsOfCraftActivity
import com.squareup.picasso.Picasso

class Adapter_listOfCrafs(var listOfCrafs:List<ListOfCrafs_Response>)
    : RecyclerView.Adapter<Adapter_listOfCrafs.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context)
            .inflate(R.layout.rcyitem_listofcrafs,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = listOfCrafs[position]
        var name:String=ItemsViewModel.Craftsmans_By_Craftsman_Type[position].first_name
        var last:String=ItemsViewModel.Craftsmans_By_Craftsman_Type[position].last_name
        holder.textView.text= name+" "+last
        holder.crafs_desc.text= ItemsViewModel.Craftsmans_By_Craftsman_Type[position].description
       holder.ratingQuantity.text= ItemsViewModel.Craftsmans_By_Craftsman_Type[position].craftsman_rate.toString()
        Picasso.get().load(ItemsViewModel.Craftsmans_By_Craftsman_Type[position].craftsman_img).into(holder.imageView)

        holder.itemView.setOnClickListener {

            val context=holder.textView.context

            var intent= Intent(context, DetailsOfCraftActivity::class.java)
            intent.putExtra("craftsman_id",ItemsViewModel.Craftsmans_By_Craftsman_Type[position].craftsman_id)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return listOfCrafs.size
    }


    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.img_craftsman)
        val textView: TextView = itemView.findViewById(R.id.txt_crafsName)
        val crafs_desc: TextView = itemView.findViewById(R.id.txt_crafs_desc)
        val ratingQuantity: TextView = itemView.findViewById(R.id.txt_ratingQuantity)

    }

}