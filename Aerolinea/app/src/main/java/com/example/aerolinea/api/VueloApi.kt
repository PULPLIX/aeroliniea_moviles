package com.example.aerolinea.api

import com.example.aerolinea.Model.Ciudad
import com.example.aerolinea.Model.Vuelo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VueloApi {

    @GET("vuelos/listar")
    suspend fun getVuelos(): Response<List<Vuelo>>


}