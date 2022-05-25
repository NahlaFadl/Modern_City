package com.example.modern_city.Models.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modern_city.API_SERVIECS.ListOfPlaceType
import com.example.modern_city.R
import com.example.modern_city.ui.categories.Categories_CHB
import com.example.modern_city.ui.categories.CategoryDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recycler_chb.view.*
import kotlinx.android.synthetic.main.place_catagory.view.*

class RecyclerCHB_Adapter(var places: List<ListOfPlaceType>):RecyclerView.Adapter<RecyclerCHB_Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.place_catagory,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemviewmodel=places[position]
        holder.txt.text=itemviewmodel.places_by_place_type[position].place_name
        //holder.txtDescr.text=itemviewmodel.places_by_place_type[position].description.toString()
        Picasso.get().load(itemviewmodel.places_by_place_type[position].big_img).into(holder.photo)
        val context=holder.txt.context

        holder.txt.setOnClickListener {
            var intent= Intent(context, CategoryDetailsActivity::class.java)
            intent.putExtra("place_id",itemviewmodel.places_by_place_type[position].place_id)
            intent.putExtra("phone",itemviewmodel.places_by_place_type[position].phone)
            intent.putExtra("Photo",itemviewmodel.places_by_place_type[position].big_img)
            intent.putExtra("place_name",itemviewmodel.places_by_place_type[position].place_name)
            context.startActivity(intent)
        }


//        holder.CHB_Txt.setText(places.get(position).title)
//
//        places.get(position).photo?.let { holder.CHB_Photo.setImageResource(it) }
    }

    override fun getItemCount(): Int {
        return places.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

//        val CHB_Photo=itemView.CHB_Image as ImageView
//        val CHB_Txt: TextView = itemView.CHB_Txt
        val photo=itemView.image as ImageView
        val txt: TextView = itemView.txt_name
        val txtDescr: TextView = itemView.txt_description

    }
}