package com.example.modern_city.Models

class Loginresponse(var status:Boolean, var errNum:Int, var msg:String, var user:User){



}
class User(var user_id:Int,var user_group_id:Int,var city_id:Int,
           var first_name:String ,var last_name:String
           ,var email:String ,var gender:String
           ,var address:String ,var phone:String
           ,var user_img:String
           ,var token:String , ){



}
//
//"status": true,
//"errNum": "0000",
//"msg": "This Data is selected",
//"User": {
//    "user_id": 18,
//    "user_group_id": 1,
//    "city_id": 1,
//    "first_name": "Anas",
//    "last_name": "Hassan",
//    "email": "xavi22her@gmail.com",
//    "gender": "Male",
//    "address": "Minia",
//    "phone": "01111911110",
//    "user_img": null,
//    "token": "UbdTXFME9I3vtJ1q3ORi2022-04-18 22:46:45IjtdjCY0tpMXQRUO6YVLK"