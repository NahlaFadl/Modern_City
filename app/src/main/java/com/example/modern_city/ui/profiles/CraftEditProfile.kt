package com.example.modern_city.ui.profiles

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.modern_city.API_SERVIECS.ApiClient
import com.example.modern_city.API_SERVIECS.CraftEditResponse
import com.example.modern_city.API_SERVIECS.EditUserResponse
import com.example.modern_city.CraftsUploadPhoto.MainUploadCraftPhotoActivity
import com.example.modern_city.R
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

        // to upload the Craft edit
        Craft_done.setOnClickListener {
            editCraftInfo(token.toString(),edit_nameCraft.text,edit_CraftlastName.text,
                edit_CraftPassword.text, edit_CraftAddress.text,edit_CraftPhone.text)
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
}