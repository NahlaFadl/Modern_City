package com.example.modern_city

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler_chb.view.*
import java.util.ArrayList

class RecyclerCHB_Adapter(var places: List<CategoriesModuel>):RecyclerView.Adapter<RecyclerCHB_Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_chb,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.CHB_Txt.setText(places.get(position).title)
        places.get(position).photo?.let { holder.CHB_Photo.setImageResource(it) }
    }

    override fun getItemCount(): Int {
        return places.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val CHB_Photo=itemView.CHB_Image as ImageView
        val CHB_Txt: TextView = itemView.CHB_Txt
    }
}