package com.example.modern_city.Models.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.modern_city.R
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

class Adapter_slider: SliderViewAdapter<Adapter_slider.VH>()  {
    private var mSliderItems = ArrayList<String>()
    fun renewItems(sliderItems: ArrayList<String>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }
    fun addItem(sliderItem: String) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }


    override fun getCount(): Int {
        return mSliderItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): VH {
        val inflate: View = LayoutInflater.from(parent?.context).inflate(R.layout.slider_item, null)
        return VH(inflate)
    }

    override fun onBindViewHolder(viewHolder: VH?, position: Int) {
        Picasso.get().load(mSliderItems[position]).fit().into(viewHolder?.imageView)
    }



    class VH(itemView: View) : ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.item_imageSlider)

    }
}