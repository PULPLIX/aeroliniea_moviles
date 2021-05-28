package com.example.aerolinea.Daos

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.aerolinea.Model.Ciudad
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.api.RetrofitInstance
import retrofit2.Response
import retrofit2.http.Path
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DaoVuelo {
    suspend fun getVuelos(): Response<List<Vuelo>> {
        return RetrofitInstance.apiVuelo.getVuelos()
    }

    suspend fun buscarVuelos(modalidad: String, origen: String, destino: String, fechaI: String, fechaF: String, descuento: String): Response<List<Vuelo>> {
        return RetrofitInstance.apiVuelo.buscarVuelos(modalidad,origen,destino,fechaI,fechaF,descuento)
    }
}