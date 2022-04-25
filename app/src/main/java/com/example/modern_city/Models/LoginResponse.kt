package com.example.modern_city.Models

class Loginresponse(var status:Boolean, var errNum:Int, var msg:String, var data:data){



}
class data(var user_id:Int,var user_group_id:Int,var city_id:Int,
           var first_name:String ,var last_name:String
           ,var email:String ,var gender:String
           ,var address:String ,var phone:String
           ,var user_img:String
           ,var token:String , ){



}
