package com.example.aerolinea.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aerolinea.Daos.DaoCiudad
import com.example.aerolinea.Daos.DaoVuelo
import com.example.aerolinea.ViewModel.HomeViewModel
import com.example.aerolinea.ViewModel.MainViewModel

class HomeViewModelFactory(private val repository: DaoVuelo): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}