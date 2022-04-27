package com.example.modern_city.API_SERVIECS

data class ListOfPlaceType(
    val Places_By_Place_Type: List<PlacesByPlaceType>,
    val errNum: String,
    val msg: String,
    val status: Boolean
)



data class PlacesByPlaceType(
    val big_img: Any,
    val description: Any,
    val phone: String,
    val place_id: Int,
    val place_name: String,
    val place_type_name: String
)