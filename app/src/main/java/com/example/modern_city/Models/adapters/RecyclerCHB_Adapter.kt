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
import kotlinx.android.synthetic.main.item_recycler_chb.view.*

class RecyclerCHB_Adapter(var places: List<ListOfPlaceType>):RecyclerView.Adapter<RecyclerCHB_Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_chb,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemviewmodel=places[position]


        holder.CHB_Txt.text=itemviewmodel.Places_By_Place_Type[position].place_name
        val context=holder.CHB_Txt.context
        var intent= Intent(context, CategoryDetailsActivity::class.java)
        intent.putExtra("place_id",itemviewmodel.Places_By_Place_Type[position].place_id)

        context.startActivity(intent)

//        holder.CHB_Txt.setText(places.get(position).title)
//
//        places.get(position).photo?.let { holder.CHB_Photo.setImageResource(it) }
    }

    override fun getItemCount(): Int {
        return places.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val CHB_Photo=itemView.CHB_Image as ImageView
        val CHB_Txt: TextView = itemView.CHB_Txt

    }
}