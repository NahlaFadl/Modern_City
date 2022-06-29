package com.example.modern_city.ui.profiles

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.DetailsUserResponse
import com.example.modern_city.API_SERVIECS.EditUserResponse
import com.example.modern_city.R
import com.example.modern_city.UploadPhoto.MainUploadPhotoActivity
import com.example.modern_city.ui.auth.LoginActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_user_edit_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserEditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit_profile)

        val sharedPreferences = this.getSharedPreferences("userInfo_login", Context.MODE_PRIVATE)
        var token=  sharedPreferences.getString("token",null)
        var id=  sharedPreferences.getInt("user_id",0)
        var pass=  sharedPreferences.getString("Password",null)
        // to get Details of user
        showUserDetails(token.toString(),id)

        // to upload the user edit
        done.setOnClickListener {

            if (edit_oldPassword.text.toString().equals(pass)) {
                editUserInfo(
                    token.toString(), edit_name.text, edit_lastName.text,
                    edit_newPassword.text, edit_address.text, edit_phone.text
                )
            }else{
                Toast.makeText(this,"خطا في كلمة المرور القديمه",Toast.LENGTH_LONG).show()
            }
        }

        // to cancel user edit
        cancel.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("finish", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // To clean up all activities
            startActivity(intent)
            finish()
        }
        // intent to activity to change user profile photo
        btn_addUserProfilePhoto.setOnClickListener {
            val intent: Intent = Intent(this, MainUploadPhotoActivity::class.java)
            startActivity(intent)
        }
    }

    // to upload the user edit
    fun editUserInfo(token:String, fName: Editable, lName: Editable,passwod:Editable, address: Editable, phone: Editable){

        var call= ApiClient.instance?.getMyApi()?.editUser(token,fName, lName,passwod, address,phone)

        if (call != null){

            call.enqueue(object : Callback<EditUserResponse> {
                override fun onResponse(
                    call: Call<EditUserResponse>?,
                    response: Response<EditUserResponse>?
                ) {

                    Toast.makeText(this@UserEditProfile,response?.body()?.msg, Toast.LENGTH_LONG).show()

                }

                override fun onFailure(call: Call<EditUserResponse>?, t: Throwable?) {
                    Toast.makeText(this@UserEditProfile,"Faliar"
                        , Toast.LENGTH_LONG).show()
                }

            } )

        }else
            Toast.makeText(this,"Faliar222", Toast.LENGTH_LONG).show()
    }

    // to get Details of user
    fun showUserDetails(Token:String,ID:Int){
        var call= ApiClient.instance?.getMyApi()?.showUserDetails(Token,ID)

        if (call != null){

            call.enqueue(object : Callback<DetailsUserResponse> {
                override fun onResponse(
                    call: Call<DetailsUserResponse>?,
                    response: Response<DetailsUserResponse>?
                ) {

                    Toast.makeText(this@UserEditProfile,response?.body()?.details_of_user?.email, Toast.LENGTH_LONG).show()

                    //to load user profile photo
                    Picasso.get().load(response?.body()?.details_of_user?.user_img).into(editProfile_image)

                    // to get user name
                    edit_name.setText(response?.body()?.details_of_user?.first_name)
                    edit_lastName.setText(response?.body()?.details_of_user?.last_name)
                    edit_address.setText(response?.body()?.details_of_user?.address)
                    edit_phone.setText(response?.body()?.details_of_user?.phone.toString())
                }

                override fun onFailure(call: Call<DetailsUserResponse>?, t: Throwable?) {
                    Toast.makeText(this@UserEditProfile,"Faliar"
                        , Toast.LENGTH_LONG).show()
                }

            } )

        }else
            Toast.makeText(this,"Faliar222", Toast.LENGTH_LONG).show()
    }
}