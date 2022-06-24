package com.example.modern_city.API_SERVIECS

data class ListOfPlaceType(
    val errNum: String,
    val msg: String,
    val places_by_place_type: List<PlacesByPlaceType>,
    val status: Boolean
)



data class PlacesByPlaceType(
    val big_img: String,
    val description: Any,
    val phone: String,
    val geo_location_lat: Double,
    val geo_location_long: Double,
    val place_id: Int,
    val place_name: String,
    val place_rate: Int,
    val place_state: String,
    val place_type_name: String
)


