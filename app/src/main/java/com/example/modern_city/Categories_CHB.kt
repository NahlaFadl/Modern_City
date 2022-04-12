package com.example.modern_city

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_categories_chb.*
import java.util.ArrayList

class Categories_CHB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_chb)

        val place: ArrayList<CategoriesModuel> = ArrayList<CategoriesModuel>()
        place.add(CategoriesModuel("Cafe", R.drawable.cat))
        place.add(CategoriesModuel("Pharmacy", R.drawable.dog))
        place.add(CategoriesModuel("Cafe", R.drawable.cat))
        place.add(CategoriesModuel("Pharmacy", R.drawable.dog))
        place.add(CategoriesModuel("Cafe", R.drawable.cat))
        place.add(CategoriesModuel("Pharmacy", R.drawable.dog))
        place.add(CategoriesModuel("Cafe", R.drawable.cat))
        place.add(CategoriesModuel("Pharmacy", R.drawable.dog))
        val adapter = RecyclerCHB_Adapter(place)
        val layoutManager: RecyclerView.LayoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        recycler_categoriesCHB.setLayoutManager(layoutManager)
        recycler_categoriesCHB.setItemAnimator(DefaultItemAnimator())
        recycler_categoriesCHB.setAdapter(adapter)
    }
}