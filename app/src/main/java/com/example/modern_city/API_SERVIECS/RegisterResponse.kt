package com.example.modern_city.API_SERVIECS

data class RegisterResponse (var status:Boolean,var errNum:Int,var msg:String,var data:datan)


data class datan(var city_id:Int,var first_name:String,var last_name:String,
                 var email:String,var gender:String,var address:String
                , var phone:Int, var user_group_id:Int,
                var token:String, var user_id:Int)


