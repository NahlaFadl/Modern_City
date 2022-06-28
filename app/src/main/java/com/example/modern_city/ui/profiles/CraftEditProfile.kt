package com.example.modern_city.ui.profiles

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.*
import com.example.modern_city.CraftsUploadPhoto.MainUploadCraftPhotoActivity
import com.example.modern_city.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_craft_edit_profile.*
import kotlinx.android.synthetic.main.activity_user_edit_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CraftEditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craft_edit_profile)

        val sharedPreferences = this.getSharedPreferences("crafs_userinf", Context.MODE_PRIVATE)
        var token=  sharedPreferences.getString("token",null)
        var id=  sharedPreferences.getInt("user_id",0)
        var pass=  sharedPreferences.getString("password",null)

        // to upload the Craft edit
        Craft_done.setOnClickListener {
            if (edit_CraftOldPassword.text.toString().equals(pass)) {
                editCraftInfo(
                    token.toString(), edit_nameCraft.text, edit_CraftlastName.text,
                    edit_CraftNewPassword.text, edit_CraftAddress.text, edit_CraftPhone.text
                )
            }else{
                Toast.makeText(this,"خطا في كلمة المرور القديمه",Toast.LENGTH_LONG).show()
            }
        }
        // to craft edit photo of profile
        btn_addCraftProfilePhoto.setOnClickListener {
            var intent=Intent(this,MainUploadCraftPhotoActivity::class.java)
            startActivity(intent)
        }
        // to cancel Craft edit
        craft_cancel.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("finish", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // To clean up all activities
            startActivity(intent)
            finish()
        }
        // to get Details of craft
        showCraftDetailsToCrafts(token.toString(),id)
    }

    // to upload the Craft edit
    fun editCraftInfo(token:String, fName: Editable, lName: Editable, passwod: Editable, address: Editable, phone: Editable){

        var call= ApiClient.instance?.getMyApi()?.editCraft(token,fName, lName,passwod, address,phone)

        if (call != null){

            call.enqueue(object : Callback<CraftEditResponse> {
                override fun onResponse(
                    call: Call<CraftEditResponse>?,
                    response: Response<CraftEditResponse>?
                ) {

                    Toast.makeText(this@CraftEditProfile,response?.body()?.msg, Toast.LENGTH_LONG).show()

                }

                override fun onFailure(call: Call<CraftEditResponse>?, t: Throwable?) {
                    Toast.makeText(this@CraftEditProfile,"Faliar"
                        , Toast.LENGTH_LONG).show()
                }

            } )

        }else
            Toast.makeText(this,"Faliar222", Toast.LENGTH_LONG).show()
    }

    // to get Details of craft
    fun showCraftDetailsToCrafts(Token:String,ID:Int){
        var call= ApiClient.instance?.getMyApi()?.showCraftDetailsToCrafts(Token,ID)

        if (call != null){

            call.enqueue(object : Callback<ShowDetailsOfCraftToCraftResponsr> {
                override fun onResponse(
                    call: Call<ShowDetailsOfCraftToCraftResponsr>?,
                    response: Response<ShowDetailsOfCraftToCraftResponsr>?
                ) {

                    Toast.makeText(this@CraftEditProfile,response?.body()?.details_of_craftsman?.email, Toast.LENGTH_LONG).show()

                    //to load user profile photo
                    Picasso.get().load(response?.body()?.details_of_craftsman?.craftsman_img).into(craftEditProfile_image)

                    // to get user name
                    edit_nameCraft.setText(response?.body()?.details_of_craftsman?.first_name)
                    edit_CraftlastName.setText(response?.body()?.details_of_craftsman?.last_name)
                    edit_CraftAddress.setText(response?.body()?.details_of_craftsman?.address.toString())
                    edit_CraftPhone.setText(response?.body()?.details_of_craftsman?.phone.toString())
                }

                override fun onFailure(call: Call<ShowDetailsOfCraftToCraftResponsr>?, t: Throwable?) {
                    Toast.makeText(this@CraftEditProfile,"Faliar"
                        , Toast.LENGTH_LONG).show()
                }

            } )

        }else
            Toast.makeText(this,"Faliar222", Toast.LENGTH_LONG).show()
    }
}