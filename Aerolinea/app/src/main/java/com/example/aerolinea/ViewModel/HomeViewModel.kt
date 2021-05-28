package com.example.aerolinea.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aerolinea.Daos.DaoVuelo
import com.example.aerolinea.Model.Ciudad
import com.example.aerolinea.Model.Vuelo
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel (private val repository: DaoVuelo): ViewModel() {
    val myResponse: MutableLiveData<Response<Vuelo>> = MutableLiveData()
    val vuelos: MutableLiveData<Response<List<Vuelo>>> = MutableLiveData()

    fun getVuelos(){
        viewModelScope.launch{
            val response = repository.getVuelos()
            vuelos.value = response
        }
    }

    fun buscarVuelos(modalidad: String, origen: String, destino: String, fechaI: String, fechaF: String, descuento: String){
        viewModelScope.launch{
            val response = repository.buscarVuelos(modalidad, origen, destino,fechaI, fechaF, descuento)
            vuelos.value = response
        }
    }
}