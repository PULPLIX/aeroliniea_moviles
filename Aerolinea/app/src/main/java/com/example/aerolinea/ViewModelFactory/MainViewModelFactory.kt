package com.example.aerolinea.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aerolinea.Daos.DaoCiudad
import com.example.aerolinea.ViewModel.MainViewModel

class MainViewModelFactory(private val repository: DaoCiudad): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}