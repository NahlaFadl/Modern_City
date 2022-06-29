package com.example.modern_city.ui.profiles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.DetailsUserResponse
import com.example.modern_city.API_SERVIECS.LogoutResponse
import com.example.modern_city.R
import com.example.modern_city.UploadPhoto.MainUploadPhotoActivity
import com.example.modern_city.ui.HomeActivity
import com.example.modern_city.ui.auth.LoginActivity
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


        // intent to activity to user info
        txt_edit_account.setOnClickListener {
            val intent:Intent=Intent(this,UserEditProfile::class.java)
            startActivity(intent)
        }

        // call function to change show profile photo and user name
        showUserDetails(token.toString(),id!!.toInt())

        // click to call logout function and log out from application
        user_LogoutImage.setOnClickListener {
            userLogout(token.toString())
            finish()
        }
    }

    // to change show profile photo and user name
    fun showUserDetails(Token:String,ID:Int){
        var call= ApiClient.instance?.getMyApi()?.showUserDetails(Token,ID)

        if (call != null){

            call.enqueue(object : Callback<DetailsUserResponse> {
                override fun onResponse(
                    call: Call<DetailsUserResponse>?,
                    response: Response<DetailsUserResponse>?
                ) {

                    Toast.makeText(this@ProfileActivity,response?.body()?.details_of_user?.email, Toast.LENGTH_LONG).show()

                    //to load user profile photo
                    Picasso.get().load(response?.body()?.details_of_user?.user_img).into(photo_profile)

                    // to get user name
                    var fullName=response?.body()?.details_of_user?.first_name+" "+
                            response?.body()?.details_of_user?.last_name
                    txt_name_prof.text=fullName

                    //to get user address
                    txt_titile.text=response!!.body()!!.details_of_user.address

                }

                override fun onFailure(call: Call<DetailsUserResponse>?, t: Throwable?) {
                    Toast.makeText(this@ProfileActivity,"Faliar"
                        , Toast.LENGTH_LONG).show()
                }

            } )

        }else
            Toast.makeText(this,"Faliar222", Toast.LENGTH_LONG).show()
    }

    // to log out from application
    fun userLogout(Token:String){
        var call= ApiClient.instance?.getMyApi()?.userLogOut(Token)

        if (call != null){

            call.enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(
                    call: Call<LogoutResponse>?,response: Response<LogoutResponse>?) {

                    Toast.makeText(this@ProfileActivity,response?.body()?.msg, Toast.LENGTH_LONG).show()

                    val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
                    intent.putExtra("finish", true)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // To clean up all activities
                    startActivity(intent)
                    finish()

                }

                override fun onFailure(call: Call<LogoutResponse>?, t: Throwable?) {
                    Toast.makeText(this@ProfileActivity,"Faliar"
                        , Toast.LENGTH_LONG).show()
                }

            } )

        }else
            Toast.makeText(this,"Faliar222", Toast.LENGTH_LONG).show()
    }

}