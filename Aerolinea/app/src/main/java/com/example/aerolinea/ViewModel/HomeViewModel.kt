package com.example.aerolinea.ViewModel

import android.util.Log
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
            Log.d("Vuelos",response.body().toString())
            vuelos.value = response
        }
    }
}