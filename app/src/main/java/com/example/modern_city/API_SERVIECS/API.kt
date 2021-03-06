package com.example.modern_city.API_SERVIECS


import android.text.Editable
import com.example.modern_city.Models.CategoriesRespon
//import com.example.modern_city.Models.Loginresponse
import retrofit2.Call
import retrofit2.http.*

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
        ): Call<UserRegister>

    //registercrafs
    @FormUrlEncoded
    @POST("api/store_crafts")
    @Headers("Accept: application/json")
    fun registerCrafts(
        @Field("craftsman_type_id")craftsman_type_id:Int,
        @Field("city_id")city_id:Int,
        @Field("first_name")first_name:String,
        @Field("last_name")last_name:String,
        @Field("email")email:String,
        @Field("gender")gender:String,
        @Field("password")password:String,
       @Field("address")address:String,
       @Field("phone")phone:String
    ): Call<Crafs_Register_Responces>

     //login
    @FormUrlEncoded
    @POST("api/login")
    @Headers("Accept: application/json")
     fun login(
         @Field("email") email:String,
         @Field("password") password:String,
         @Field("login_type") login_type:String,
     ):Call<LoginRespons>

    //craft login
    @FormUrlEncoded
    @POST("api/login")
    @Headers("Accept: application/json")
    fun craftLogin(
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("login_type") login_type:String,
    ):Call<LoginCraftResponse>

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
        @Field("place_id") place_id:Int
    ):Call<PlaceDetailsResponse>

    //git crafsType
    @FormUrlEncoded
    @POST("api/auth/craftsman_type/show_all_crafts_types")
    @Headers("Accept: application/json")
    fun gitCrafsTypes(
        @Field("token") token:String


    ):Call<CrafsTypes_Response>



    //////getCrafsByType
    @FormUrlEncoded
    @POST("api/auth/craftsman/show_crafts_by_craftsman_type")
    @Headers("Accept: application/json")
    fun getCrafsByType(
        @Field("token") token:String,
        @Field("craftsman_type_id") craftsman_type_id:Int

    ):Call<ListOfCrafs_Response>

    //////crafdetails
    @FormUrlEncoded
    @POST("api/auth/craftsman/show_details_of_craftsman")
    @Headers("Accept: application/json")
    fun getCrafsDetails(

        @Field("token") token:String,
        @Field("craftsman_id") craftsman_id:Int

    ):Call<Crafs_Details_Responce>


    //showFavourite
    @FormUrlEncoded
    @POST("api/auth/favourite_places_list/show_favorite")
    @Headers("Accept: application/json")
    fun showFavourite(
        @Field("token") token:String
    ):Call<ShowFavouriteResponse>

    //addFavourites
    @FormUrlEncoded
    @POST("api/auth/favourite_places_list/add_to_favorite")
    @Headers("Accept: application/json")
    fun addFavourites(
        @Field("token") token:String,
        @Field("place_id") place_id:Int
    ):Call<AddFavourite>

    //deleteFavourites
    @FormUrlEncoded
    @POST("api/auth/favourite_places_list/delete_from_favorite")
    @Headers("Accept: application/json")
    fun deleteFavourites(
        @Field("token") token:String,
        @Field("place_id") place_id:Int
    ):Call<DeleteFavourite>

    //FamousPlace
    @FormUrlEncoded
    @POST("api/auth/places/show_famous_places")
    @Headers("Accept: application/json")
    fun getFamousPlace(
        @Field("token") token:String
    ):Call<FamousPlacesResponse>

    //showUserDetails
    @FormUrlEncoded
    @POST("api/show_details_of_user")
    @Headers("Accept: application/json")
    fun showUserDetails(
        @Field("token") token:String,
        @Field("user_id") place_id:Int
    ):Call<DetailsUserResponse>
    //showCraftDetailsToCrafts
    @FormUrlEncoded
    @POST("api/authenticate/show_details_of_craftsman")
    @Headers("Accept: application/json")
    fun showCraftDetailsToCrafts(
        @Field("token") token:String,
        @Field("craftsman_id") craftsman_id:Int
    ):Call<ShowDetailsOfCraftToCraftResponsr>
    //////userLogOut
    @FormUrlEncoded
    @POST("api/logout_user")
    @Headers("Accept: application/json")
    fun userLogOut(
        @Field("token") token:String
    ):Call<LogoutResponse>

    //////craft LogOut
    @FormUrlEncoded
    @POST("api/logout_crafts")
    @Headers("Accept: application/json")
    fun craftLogOut(
        @Field("token") token:String
    ):Call<CraftLogotResonse>

    //////Edit User
    @FormUrlEncoded
    @POST("api/edit_user")
    @Headers("Accept: application/json")
    fun editUser(
        @Field("token") token:String,
        @Field("first_name") first_name: Editable,
        @Field("last_name") last_name: Editable,
        @Field("password") password: Editable,
        @Field("address") address: Editable,
        @Field("phone") phone:Editable
    ):Call<EditUserResponse>

    //////Edit Craft
    @FormUrlEncoded
    @POST("api/authenticate/edit_craftsman")
    @Headers("Accept: application/json")
    fun editCraft(
        @Field("token") token:String,
        @Field("first_name") first_name: Editable,
        @Field("last_name") last_name: Editable,
        @Field("password") password: Editable,
        @Field("address") address: Editable,
        @Field("phone") phone:Editable,
        @Field("description") description:Editable
    ):Call<CraftEditResponse>

    //////Craft Status Response
    @FormUrlEncoded
    @POST("api/authenticate/edit_craftsman_status")
    @Headers("Accept: application/json")
    fun craftStatus(
        @Field("token") token:String,
        @Field("status") status:Int

    ):Call<CrafStatusResponse>



    //get_crafts_Spinner

    @GET("/api/get_crafts_type")
    fun spinerList_crafs():Call<Spiner_list_responce>

    //   Adds
    @FormUrlEncoded
    @POST("api/auth/places/show_advertisement")
    @Headers("Accept: application/json")
    fun gitAdds(
        @Field("token") token:String
    ):Call<Adds_Responces>

    //givePlace_Rating
    @FormUrlEncoded
    @POST("api/auth/rate/add_rate_place")
    @Headers("Accept: application/json")
    fun givePlace_Rating(
        @Field("token") token:String,
        @Field("place_id") place_id:Int,
        @Field("rate") rate:Int
    ):Call<Base_Responce>



    //////getStation
    @FormUrlEncoded
    @POST("api/auth/bus_routes/get_bus_route_name")
    @Headers("Accept: application/json")
    fun getStation(
        @Field("token") token:String,

    ):Call<StationsTypesResponces>



   ///////getStationRoutes

    @FormUrlEncoded
    @POST("api/auth/bus_routes/get_bus_route_station")
    @Headers("Accept: application/json")
    fun getStationRoutes(
        @Field("token") token:String,
        @Field("bus_route_id") bus_route_id:Int
        ):Call<StationRoutsResponce>



    //////////getNearstPlace
    @FormUrlEncoded
    @POST("api/auth/places/get_nearest_place")
    @Headers("Accept: application/json")
    fun getNearstPlace(
        @Field("token") token:String,
        @Field("place_type_id") place_type_id:Int,
        @Field("geo_location_lat") geo_location_lat:String,
        @Field("geo_location_long") geo_location_long:String
    ):Call<NearestPlaceResponce>



}