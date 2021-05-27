package com.example.aerolinea.Daos

import com.example.aerolinea.Model.Ciudad
import com.example.aerolinea.api.RetrofitInstance
import retrofit2.Response

class DaoCiudad {
    suspend fun getCiudad(id:Int): Response<Ciudad> {
        return RetrofitInstance.api.getCiudad(id)
    }

    suspend fun getCiudades(): Response<List<Ciudad>> {
        return RetrofitInstance.api.getCiudades()
    }
}