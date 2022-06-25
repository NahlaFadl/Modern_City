package com.example.modern_city.API_SERVIECS

data class Spiner_list_responce(
    val errNum: String,
    val get_crafts_type: List<GetCraftsType>,
    val msg: String,
    val status: Boolean
)

data class GetCraftsType(
    val craftsman_type_id: Int,
    val craftsman_type_name: String
)