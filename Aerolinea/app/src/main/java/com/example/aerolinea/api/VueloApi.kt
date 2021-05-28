package com.example.aerolinea.api

import com.example.aerolinea.Model.Ciudad
import com.example.aerolinea.Model.Vuelo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VueloApi {

    @GET("vuelos/listar")
    suspend fun getVuelos(): Response<List<Vuelo>>

    @GET("vuelos/buscar")
    suspend fun buscarVuelos(@Query("Modalidad") modalidad: String, @Query("idOrigen") origen: String, @Query("idDestino") destino: String, @Query("fechaI") fechaI: String, @Query("fechaF") fechaF: String, @Query("descuento") descuento: String ): Response<List<Vuelo>>
}