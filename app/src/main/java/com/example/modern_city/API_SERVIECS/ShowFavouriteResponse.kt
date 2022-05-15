package com.example.modern_city.API_SERVIECS

data class ShowFavouriteResponse(
    val Places_Favourite: List<PlacesFavourite>,
    val errNum: String,
    val msg: String,
    val status: Boolean
)
data class PlacesFavourite(
    val address: String,
    val big_img: String,
    val close_time: Any,
    val description: Any,
    val geo_location_lat: String,
    val geo_location_long: String,
    val open_time: Any,
    val phone: String,
    val place_id: Int,
    val place_name: String,
    val place_rate: Int,
    val place_state: String,
    val user_id: Int
)