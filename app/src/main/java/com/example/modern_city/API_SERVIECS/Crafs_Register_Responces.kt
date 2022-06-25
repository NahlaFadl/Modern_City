package com.example.modern_city.API_SERVIECS

data class Crafs_Register_Responces(
    val data: Dataa,
    val errNum: String,
    val msg: String,
    val status: Boolean
)


data class Dataa(
    val address: String,
    val city_id: String,
    val craftsman_id: Int,
    val craftsman_type_id: String,
    val email: String,
    val first_name: String,
    val gender: String,
    val last_name: String,
    val phone: String,
    val token: String
)