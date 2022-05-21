package com.example.modern_city.API_SERVIECS

data class PlaceDetailsResponse(
    val details_of_place: DetailsOfPlace,
    val errNum: String,
    val msg: String,
    val status: Boolean
)
data class DetailsOfPlace(
    val address: String,
    val big_img: String,
    val city_id: Int,
    val close_time: String,
    val created_at: String,
    val description: Any,
    val geo_location_lat: String,
    val geo_location_long: String,
    val open_time: String,
    val phone: String,
    val place_id: Int,
    val place_name: String,
    val place_rate: Int,
    val place_state: String,
    val place_type_id: Int,
    val show_in_ads: Int,
    val show_in_famous_places: Int,
    val slider_img: List<String>,
    val small_img: String,
    val updated_at: String
)

