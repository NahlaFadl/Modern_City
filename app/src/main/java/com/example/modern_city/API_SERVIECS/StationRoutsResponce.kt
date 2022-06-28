package com.example.modern_city.API_SERVIECS

data class StationRoutsResponce(
    val bus_routes_station: BusRoutesStation,
    val errNum: String,
    val msg: String,
    val status: Boolean
)


data class BusRoutesStation(
    val bus_route_name: String,
    val bus_route_stations: List<String>
)