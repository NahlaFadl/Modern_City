//package com.example.modern_city.ui.fragment
//
//import android.content.Context
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.recyclerview.widget.DefaultItemAnimator
//import androidx.recyclerview.widget.RecyclerView
//import androidx.recyclerview.widget.StaggeredGridLayoutManager
//import androidx.viewpager.widget.ViewPager
//import com.example.modern_city.API_SERVIECS.ApiClient
//import com.example.modern_city.API_SERVIECS.ListOfPlaceType
//import com.example.modern_city.API_SERVIECS.ShowFavouriteResponse
//import com.example.modern_city.Models.adapters.AdapterShowFavourite
//import com.example.modern_city.Models.adapters.MyAdapter
//import com.example.modern_city.Models.adapters.RecyclerCHB_Adapter
//import com.example.modern_city.R
//import com.google.android.material.tabs.TabLayout
//import kotlinx.android.synthetic.main.activity_categories_chb.*
//import kotlinx.android.synthetic.main.fragment_favorite.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import java.util.ArrayList
//import android.R
//
//
//
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [FavoriteFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class FavoriteFragment : Fragment() {
//
//    var tabLayout: TabLayout? = null
//    var viewPager: ViewPager? = null
//
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        var view:View
//        // Inflate the layout for this fragment
//        view= inflater.inflate(R.layout.fragment_favorite, container, false)
//
//        //tabLayout
//
//        loadData()
//        return view
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment FavoriteFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            FavoriteFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//
//    fun loadData(){
//        //val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
//
//       // var token=sharedPreferences.getString("token",null).toString()
//
//
//       // if (token!=null){
//
//
//
//           // var id:Int= intent.extras?.get("typeId") as Int
//
//
//            Log.d("id",id.toString())
//            var call= ApiClient.instance?.getMyApi()?.showFavourite("ePxEAyru7FYJJY9Xj9FC2022-05-01 13:17:22kArzXqauinsiu4MRrPtoO")
//            if (call!=null){
//
//
//                call.enqueue(object: Callback<ShowFavouriteResponse> {
//                    override fun onResponse(
//                        call: Call<ShowFavouriteResponse>?,
//                        response: Response<ShowFavouriteResponse>?
//                    ) {
//                        Toast.makeText(activity,response?.body()?.msg.toString(), Toast.LENGTH_LONG).show()
//                        var listSize:Int?=response?.body()?.Places_Favourite?.size
//                        var placeArray: ArrayList<ShowFavouriteResponse> = ArrayList()
//                        for (i in  1..listSize!!){
//
//                            placeArray.add(response?.body()!!)
//                            val adapter = AdapterShowFavourite(placeArray)
//                            val layoutManager: RecyclerView.LayoutManager = StaggeredGridLayoutManager(
//                                2,
//                                StaggeredGridLayoutManager.VERTICAL
//                            )
//                            recyclerShowFavourite.setLayoutManager(layoutManager)
//                            recyclerShowFavourite.setItemAnimator(DefaultItemAnimator())
//                            recyclerShowFavourite.setAdapter(adapter)
//                        }
//
//                    }
//
//                    override fun onFailure(call: Call<ShowFavouriteResponse>?, t: Throwable?) {
//                        Toast.makeText(activity,"failure", Toast.LENGTH_LONG).show()
//                    }
//                })
//            }else
//            {
//                Toast.makeText(activity,"Call equal null", Toast.LENGTH_LONG).show()
//            }
////        }
////        else
////        {
////            Toast.makeText(this,"token equal null", Toast.LENGTH_LONG).show()
////        }
//    }
//}