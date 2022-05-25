package com.example.modern_city.API_SERVIECS

data class CrafsTypes_Response(
    val All_Craftsman_Types: List<AllCraftsmanType>,
    val errNum: String,
    val msg: String,
    val status: Boolean
)


data class AllCraftsmanType(
    val craftsman_type_id: Int,
    val craftsman_type_img: String,
    val craftsman_type_name: String,
    val created_at: Any,
    val updated_at: String
)