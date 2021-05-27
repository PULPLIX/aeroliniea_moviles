package com.example.aerolinea.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aerolinea.Daos.DaoCiudad
import com.example.aerolinea.Model.Ciudad
import kotlinx.coroutines.launch
import retrofit2.Response
import android.util.Log

class MainViewModel(private val repository: DaoCiudad): ViewModel() {
    val myResponse: MutableLiveData<Response<Ciudad>> = MutableLiveData()
    val ciudades: MutableLiveData<Response<List<Ciudad>>> = MutableLiveData()

    fun getCiudad(id:Int){
        viewModelScope.launch{
            val response = repository.getCiudad(id)
            myResponse.value = response
        }
    }

    fun getCiudades() {
        viewModelScope.launch {
            val response = repository.getCiudades()
            Log.d("MainViewResponse",response.body().toString())
            ciudades.value = response
        }
    }
}