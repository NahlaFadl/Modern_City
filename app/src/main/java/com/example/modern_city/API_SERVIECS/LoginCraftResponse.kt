package com.example.modern_city.API_SERVIECS

data class LoginCraftResponse(
    val Craftsman: Craftsman,
    val errNum: String,
    val msg: String,
    val status: Boolean
)
data class Craftsman(
    val address: String,
    val city_id: Int,
    val craftsman_id: Int,
    val craftsman_type_id: Int,
    val description: Any,
    val email: String,
    val first_name: String,
    val gender: String,
    val last_name: String,
    val phone: String,
    val status: String,
    val token: String
)