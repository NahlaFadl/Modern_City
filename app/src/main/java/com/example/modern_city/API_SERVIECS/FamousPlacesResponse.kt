package com.example.modern_city.API_SERVIECS

data class FamousPlacesResponse(
    val errNum: String,
    val famous_places: List<FamousPlace>,
    val msg: String,
    val status: Boolean
)

data class FamousPlace(
    val place_id: Int,
    val small_img: String
)