package com.example.modern_city.API_SERVIECS

data class ListOfCrafs_Response(
    val Craftsmans_By_Craftsman_Type: List<CraftsmansByCraftsmanType>,
    val errNum: String,
    val msg: String,
    val status: Boolean
)



data class CraftsmansByCraftsmanType(
    val craftsman_id: Int,
    val craftsman_img: String,
    val craftsman_type_name: String,
    val description: String,
    val first_name: String,
    val last_name: String
)