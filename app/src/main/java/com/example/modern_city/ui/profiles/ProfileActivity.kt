package com.example.modern_city.ui.profiles

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.DetailsUserResponse
import com.example.modern_city.API_SERVIECS.LoginRespons
import com.example.modern_city.R
import com.example.modern_city.UploadPhoto.MainUploadPhotoActivity
import com.example.modern_city.ui.HomeActivity
import com.example.modern_city.ui.categories.Categories_CHB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
        var token=  sharedPreferences.getString("token",null)
        var id=  sharedPreferences.getInt("user_id",0)

        btn_addProfilePhoto.setOnClickListener {
            val intent:Intent= Intent(this, MainUploadPhotoActivity::class.java)
            startActivity(intent)
        }

        showUserDetails(token.toString(),id!!.toInt())
    }

    fun showUserDetails(Token:String,ID:Int){
        var call= ApiClient.instance?.getMyApi()?.showUserDetails(Token,ID)

        if (call != null){

            call.enqueue(object : Callback<DetailsUserResponse> {
                override fun onResponse(
                    call: Call<DetailsUserResponse>?,
                    response: Response<DetailsUserResponse>?
                ) {

                    Toast.makeText(this@ProfileActivity,response?.body()?.details_of_user?.email, Toast.LENGTH_LONG).show()

                    Picasso.get().load(response?.body()?.details_of_user?.user_img).into(photo_profile)
                    var fullName=response?.body()?.details_of_user?.first_name+" "+
                            response?.body()?.details_of_user?.last_name
                    
                    txt_name_prof.text=fullName

                }

                override fun onFailure(call: Call<DetailsUserResponse>?, t: Throwable?) {
                    Toast.makeText(this@ProfileActivity,"Faliar"
                        , Toast.LENGTH_LONG).show()
                }

            } )



        }else
            Toast.makeText(this,"Faliar222"
                , Toast.LENGTH_LONG).show()




    }

}