package com.example.modern_city.API_SERVIECS

data class CategoriesRespon(
    val All_Places_Types: List<AllPlacesType>,
    val errNum: String,
    val msg: String,
    val status: Boolean
)

data class AllPlacesType(
    val place_type_id: Int,
    val place_type_img: String,
    val place_type_name: String
)