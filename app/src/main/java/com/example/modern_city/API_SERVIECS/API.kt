package com.example.modern_city.API_SERVIECS


import com.example.modern_city.Models.Loginresponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface API {

    //register
    @FormUrlEncoded
    @POST("api/store_user")
    @Headers("Accept: application/json")
    fun register(
        @Field("city_id")city_id:Int,
        @Field("first_name")first_name:String,
        @Field("last_name")last_name:String,
        @Field("email")email:String,
        @Field("gender")gender:String,
        @Field("password")password:String,
        @Field("address")address:String,
        @Field("phone")phone:String
        ): Call<RegisterResponse>

     //login
    @FormUrlEncoded
    @POST("api/store_user")
    @Headers("Accept: application/json")
     fun login(
         @Field("mobile_number") email:String,
         @Field("password") password:String,

     ):Call<Loginresponse>

}