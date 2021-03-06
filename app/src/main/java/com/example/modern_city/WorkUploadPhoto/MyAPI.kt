package com.example.modern_city.WorkUploadPhoto

import com.example.modern_city.UploadPhoto.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface MyAPI {


    @Multipart
    @POST("api/authenticate/upload_single_image")
    fun uploadImage(
        @Part craftsman_slider: MultipartBody.Part,
        //@Part("desc") desc: RequestBody
        @Part("token") token: RequestBody
    ): Call<UploadResponse>

    companion object {
        operator fun invoke(): MyAPI {
            return Retrofit.Builder()
                .baseUrl("https://btmteamwork.com/sys/mass3ood/new_modern_city/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyAPI::class.java)
        }
    }
}