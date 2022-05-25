package com.example.modern_city.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.ListOfCrafs_Response
import com.example.modern_city.Models.adapters.Adapter_listOfCrafs
import com.example.modern_city.Models.adapters.Adapter_rcy_craftsTypes
import com.example.modern_city.R
import kotlinx.android.synthetic.main.activity_catagories.*
import kotlinx.android.synthetic.main.activity_list_of_crafts.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ListOfCrafts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_crafts)

        try{
        val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
        var token=  sharedPreferences.getString("token",null)
        //var id: =intent.extras?.getString("craftsman_type_id_l")
            var id:Int= intent.extras?.get("craftsman_type_id_l") as Int

            shimmar_listcrafs.startShimmer()

        if (token != null){

                loaddata(token,id)



        }else{
            Toast.makeText(this@ListOfCrafts,"tokent =0", Toast.LENGTH_LONG).show()
        }


        }catch (e:Exception)
        {
            throw  e
        }
    }



private fun loaddata(token:String,id:Int){

    val call=ApiClient.instance?.getMyApi()?.getCrafsByType(token,id)
    if (call!=null){
        try {
            call.enqueue(object:retrofit2.Callback<ListOfCrafs_Response>{
                override fun onResponse(
                    call: Call<ListOfCrafs_Response>?,
                    response: Response<ListOfCrafs_Response>?

                ) {


                    shimmar_listcrafs.stopShimmer()
                    shimmar_listcrafs.visibility = View.GONE


                    var listsize:Int?=response?.body()?.Craftsmans_By_Craftsman_Type?.size
                    var crafsArray:ArrayList<ListOfCrafs_Response> = ArrayList()
                    for(i in 1..listsize!!){
                        rcy_listofcrafs.visibility=View.VISIBLE
                        crafsArray.add(response?.body()!!)

                        val layoutManager: RecyclerView.LayoutManager = StaggeredGridLayoutManager(
                            1,
                            StaggeredGridLayoutManager.VERTICAL
                        )
                        rcy_listofcrafs.setLayoutManager(layoutManager)
                        rcy_listofcrafs.setItemAnimator(DefaultItemAnimator())
                        val adapter = Adapter_listOfCrafs(crafsArray!!)
                        rcy_listofcrafs.adapter = adapter
                    }

                }

                override fun onFailure(call: Call<ListOfCrafs_Response>?, t: Throwable?) {
                    Toast.makeText(this@ListOfCrafts,"fallier", Toast.LENGTH_LONG).show()
                }


            })






        }catch (e:Exception)
        {
            throw e
        }

    }else
        Toast.makeText(this@ListOfCrafts,"call =0", Toast.LENGTH_LONG).show()


}
}