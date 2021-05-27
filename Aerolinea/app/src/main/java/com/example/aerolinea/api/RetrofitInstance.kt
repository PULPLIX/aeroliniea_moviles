package com.example.aerolinea.api

import com.example.aerolinea.util.Constans.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: CiudadApi by lazy {
        retrofit.create(CiudadApi::class.java)
    }

    val apiVuelo: VueloApi by lazy {
        retrofit.create(VueloApi::class.java)
    }
}