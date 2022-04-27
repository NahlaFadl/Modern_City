package com.example.modern_city.API_SERVIECS


import com.example.modern_city.Models.CategoriesRespon
//import com.example.modern_city.Models.Loginresponse
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
        @Field("phone")phone:Long
        ): Call<UserRegister>

     //login
    @FormUrlEncoded
    @POST("api/login")
    @Headers("Accept: application/json")
     fun login(
         @Field("email") email:String,
         @Field("password") password:String,
         @Field("login_type") login_type:String,
     ):Call<LoginRespons>

     //git category
     @FormUrlEncoded
     @POST("api/auth/places_types/show_all_places_types")
     @Headers("Accept: application/json")
     fun gitCategory(
         @Field("token") token:String


         ):Call<CategoriesRespon>

///gitPlaceByPlaceType
    @FormUrlEncoded
    @POST("api/auth/places/show_places_by_place_type")
    @Headers("Accept: application/json")
    fun gitPlaceByPlaceType(
        @Field("token") token:String,
        @Field("place_type_id") place_type_id:Int

    ):Call<ListOfPlaceType>
//////placeDetails
    @FormUrlEncoded
    @POST("api/auth/places/show_details_of_place")
    @Headers("Accept: application/json")
    fun getPlaceDetails(
        @Field("token") token:String,
        @Field("place_type_id") place_id:Int

    ):Call<PlaceDetailsResponse>


}