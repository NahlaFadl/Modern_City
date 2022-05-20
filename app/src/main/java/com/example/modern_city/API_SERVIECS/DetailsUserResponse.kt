package com.example.modern_city.API_SERVIECS

data class DetailsUserResponse(
    val details_of_user: DetailsOfUser,
    val errNum: String,
    val msg: String,
    val status: Boolean
)

data class DetailsOfUser(
    val address: String,
    val city_id: Int,
    val email: String,
    val first_name: String,
    val gender: String,
    val last_name: String,
    val phone: Any,
    val user_id: Int,
    val user_img: String
)