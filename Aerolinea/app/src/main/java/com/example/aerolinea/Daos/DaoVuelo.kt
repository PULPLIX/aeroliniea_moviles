package com.example.aerolinea.Daos

import com.example.aerolinea.Model.Ciudad
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.api.RetrofitInstance
import retrofit2.Response

class DaoVuelo {
    suspend fun getVuelos(): Response<List<Vuelo>> {
        return RetrofitInstance.apiVuelo.getVuelos()
    }
}