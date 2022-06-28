package com.example.modern_city.API_SERVIECS

data class NearestPlaceResponce(
    val errNum: String,
    val msg: String,
    val nearest_place: List<NearestPlace>,
    val status: Boolean
)

data class NearestPlace(
    val distance: Double,
    val place_id: Int,
    val place_name: String)