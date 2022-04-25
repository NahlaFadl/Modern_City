package com.example.modern_city.API_SERVIECS

data class LoginRespons(
    val User: User,
    val errNum: String,
    val msg: String,
    val status: Boolean
)

data class User(
    val address: String,
    val city_id: Int,
    val email: String,
    val first_name: String,
    val gender: String,
    val last_name: String,
    val phone: String,
    val token: String,
    val user_group_id: Int,
    val user_id: Int,
    val user_img: Any
)