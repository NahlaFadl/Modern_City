package com.example.modern_city.API_SERVIECS


import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit

class ApiClient private constructor() {
    private val myApi:API
    fun getMyApi(): API {
        return myApi
    }

    companion object {
        @get:Synchronized
        var instance: ApiClient? = null
            get() {
                if (field == null) {
                    field = ApiClient()
                }
                return field
            }
            private set
    }

    init {
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl("https://btmteamwork.com/sys/mass3ood/new_modern_city/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        myApi = retrofit.create(API::class.java)
    }
}