package com.example.modern_city.API_SERVIECS

data class StationsTypesResponces(
    val bus_routes_name: List<BusRoutesName>,
    val errNum: String,
    val msg: String,
    val status: Boolean
)
data class BusRoutesName(
    val bus_route_id: Int,
    val bus_route_name: String
)