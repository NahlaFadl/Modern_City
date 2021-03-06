package com.example.modern_city.ui.categories

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.CrafsTypes_Response
import com.example.modern_city.Models.adapters.Adapter_rcy_craftsTypes
import com.example.modern_city.R
import kotlinx.android.synthetic.main.activity_catagories.*
import kotlinx.android.synthetic.main.activity_categories_chb.*
import kotlinx.android.synthetic.main.activity_craftsman_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrafsType : AppCompatActivity() {
    // var  rcy_crafstype =findViewById<RecyclerView>(R.id.rcy_crafstype)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catagories)
        // rcy_crafstype =findViewById<RecyclerView>(R.id.rcy_crafstype)
        prog_crafstype.visibility= View.VISIBLE
   var name:String= intent.extras?.getString("categoryName").toString()
        type_name.text=name



/////////////////////////////////////////////////////
        val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
       var token=  sharedPreferences.getString("token",null)
       // Log.d("ttt",token.toString())
        if (token != null) {
/////////////////////////////
            try {


                var call = ApiClient.instance?.getMyApi()
                    ?.gitCrafsTypes(token)
                if (call != null) {
                    call.enqueue(object : Callback<CrafsTypes_Response> {
                        override fun onResponse(
                            call: Call<CrafsTypes_Response>?,
                            response: Response<CrafsTypes_Response>?
                        ) {
                           // type_name.text=response?.body()?.All_Craftsman_Types?.craftsman_type_name

                            var listSize: Int? = response?.body()?.All_Craftsman_Types?.size
                            var crafsArray: ArrayList<CrafsTypes_Response> = ArrayList()

                            for (i in 1..listSize!!) {
                                crafsArray.add(response?.body()!!)


                                val layoutManager: RecyclerView.LayoutManager = StaggeredGridLayoutManager(
                                    2,
                                    StaggeredGridLayoutManager.VERTICAL
                                )
                                rcy_crafstype.setLayoutManager(layoutManager)
                                rcy_crafstype.setItemAnimator(DefaultItemAnimator())
                                val adapter = Adapter_rcy_craftsTypes(crafsArray!!)
                                rcy_crafstype.adapter = adapter

                            }
                            prog_crafstype.visibility= View.INVISIBLE

                        }

                        override fun onFailure(call: Call<CrafsTypes_Response>?, t: Throwable?) {
                            Toast.makeText(this@CrafsType, "responce =0", Toast.LENGTH_LONG).show()
                        }
                    })


                } else
                    Toast.makeText(this@CrafsType, "call =0", Toast.LENGTH_LONG).show()


            }catch (e:Exception){
                throw e

            }

            ////////////////////////////////////////

        }else
        {
            Toast.makeText(this@CrafsType,"tokent =0",Toast.LENGTH_LONG).show()        }


    }
}

//fun ladData(token:String){
//    var call= ApiClient.instance?.getMyApi()?.gitCrafsTypes(token)
//    if (call!=null){
//    call.enqueue(object :Callback<CrafsTypes_Response>{
//        override fun onResponse(
//            call: Call<CrafsTypes_Response>?,
//            response: Response<CrafsTypes_Response>?
//        ) {
//
//
//            val layoutManager: RecyclerView.LayoutManager = StaggeredGridLayoutManager(
//                2,
//                StaggeredGridLayoutManager.VERTICAL
//            )
//            rcy_crafstype.setLayoutManager(layoutManager)
//            rcy_crafstype.setItemAnimator(DefaultItemAnimator())
//
//
//
//            var listSize: Int? = response?.body()?.All_Craftsman_Types?.size
//            var crafsArray: ArrayList<CrafsTypes_Response> = ArrayList()
//
//            for (i in 1..listSize!!) {
//                crafsArray.add(response?.body()!!)
//            }
//
//            val adapter = Adapter_rcy_craftsTypes(crafsArray!!)
//            rcy_crafstype.adapter = adapter
//
//        }
//
//        override fun onFailure(call: Call<CrafsTypes_Response>?, t: Throwable?) {
//            TODO("Not yet implemented")
//        }
//    })
//
//
//    }
//}