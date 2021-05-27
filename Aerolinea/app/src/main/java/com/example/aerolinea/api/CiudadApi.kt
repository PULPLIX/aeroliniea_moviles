package com.example.aerolinea.api

import com.example.aerolinea.Model.Ciudad
import com.example.aerolinea.Model.Vuelo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CiudadApi {
    @GET("ciudades/get/{idCiudad}")
    suspend fun getCiudad(@Path("idCiudad") id: Int): Response<Ciudad>

    @GET("ciudades/listar")
    suspend fun getCiudades(): Response<List<Ciudad>>
}