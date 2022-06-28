package com.example.modern_city.API_SERVIECS

data class ShowDetailsOfCraftToCraftResponsr(
    val details_of_craftsman: DetailsOfCraftsmanX,
    val errNum: String,
    val msg: String,
    val status: Boolean
)

data class DetailsOfCraftsmanX(
    val address: String,
    val city_id: Int,
    val craftsman_id: Int,
    val craftsman_img: String,
    val craftsman_rate: Int,
    val craftsman_slider: List<String>,
    val craftsman_type_id: Int,
    val description: Any,
    val email: String,
    val first_name: String,
    val gender: String,
    val last_name: String,
    val phone: String,
    val status: Any
)