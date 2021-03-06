package com.example.modern_city.Models.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.API_SERVIECS.ListOfPlaceType
import com.example.modern_city.API_SERVIECS.ShowFavouriteResponse
import com.example.modern_city.R
import com.example.modern_city.ui.categories.CategoryDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recycler_chb.view.*
import kotlinx.android.synthetic.main.item_recycler_favourite.view.*

class AdapterShowFavourite (var favouritePlaces: List<ShowFavouriteResponse>): RecyclerView.Adapter<AdapterShowFavourite.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_chb,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemviewmodel=favouritePlaces[position]


        holder.CHB_Txt.text=itemviewmodel.Places_Favourite[position].place_name
        Picasso.get().load(itemviewmodel.Places_Favourite!![position].big_img).into(holder.CHB_Photo)
        holder.CHB_Txt.setOnClickListener {
            val context = holder.CHB_Txt.context
            var intent = Intent(context, CategoryDetailsActivity::class.java)
            intent.putExtra("place_id",itemviewmodel.Places_Favourite[position].place_id)
            intent.putExtra("phone",itemviewmodel.Places_Favourite[position].phone)
            intent.putExtra("Photo",itemviewmodel.Places_Favourite[position].big_img)

            context.startActivity(intent)
        }
//        holder.CHB_Txt.setText(places.get(position).title)
//
//        places.get(position).photo?.let { holder.CHB_Photo.setImageResource(it) }
    }

    override fun getItemCount(): Int {
        return favouritePlaces.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val CHB_Photo=itemView.CHB_Image as ImageView
        val CHB_Txt: TextView = itemView.CHB_Txt

    }
}