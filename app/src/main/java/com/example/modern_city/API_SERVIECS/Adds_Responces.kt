package com.example.modern_city.API_SERVIECS

data class Adds_Responces(
    val errNum: String,
    val msg: String,
    val show_advertisement: List<ShowAdvertisement>,
    val status: Boolean
)

data class ShowAdvertisement(
    val place_id: Int,
    val small_img: String
)