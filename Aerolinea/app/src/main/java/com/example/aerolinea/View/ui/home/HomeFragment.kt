package com.example.aerolinea.View.ui.home

import android.R
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aerolinea.Daos.DaoCiudad
import com.example.aerolinea.Daos.DaoVuelo
import com.example.aerolinea.Model.Ciudad
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.View.ui.DatePickerFragment
import com.example.aerolinea.ViewModel.HomeViewModel
import com.example.aerolinea.ViewModel.MainViewModel
import com.example.aerolinea.ViewModelFactory.HomeViewModelFactory
import com.example.aerolinea.ViewModelFactory.MainViewModelFactory
import com.example.aerolinea.adapters.VuelosResultAdapter
import com.example.aerolinea.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    var ciudades: ArrayList<String> = ArrayList()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var vuelos: ArrayList<Vuelo>
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.etSalida.setOnClickListener { showDatePickerDialog(binding.etSalida) }
        binding.etRegreso.setOnClickListener { showDatePickerDialog(binding.etRegreso) }
        spinnerOrigen()
        spinnerDestino()
        vuelos = listaVuelos()
        initRecycler()
        return root
    }

    fun spinnerOrigen(){
        val repository = DaoCiudad()
        val viewModelFactory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.getCiudades()
        mainViewModel.ciudades.observe(viewLifecycleOwner, Observer {
                response ->
            if(response.isSuccessful){
                ciudades.clear()
                response.body()?.forEach{ ciudad->
                    ciudades.add(ciudad.nombre)
                }
            }else{
                Log.d("Response", response.code().toString())
            }
        })
        var adapter = ArrayAdapter(this.requireContext(), R.layout.simple_spinner_item, ciudades)
        binding.etOrigen?.setAdapter<ArrayAdapter<String>>(adapter)
        binding.etOrigen.adapter
    }

    fun spinnerDestino(){
        val repository = DaoCiudad()
        val viewModelFactory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.getCiudades()
        mainViewModel.ciudades.observe(viewLifecycleOwner, Observer {
                response ->
            if(response.isSuccessful){
                ciudades.clear()
                response.body()?.forEach{ ciudad->
                    ciudades.add(ciudad.nombre)
                }
            }else{
                Log.d("Response", response.code().toString())
            }
        })
        var adapter = ArrayAdapter(this.requireContext(), R.layout.simple_spinner_item, ciudades)
        binding.etDestino?.setAdapter<ArrayAdapter<String>>(adapter)
        binding.etDestino.adapter
    }

    fun listaVuelos(): ArrayList<Vuelo>{
        var vuelosTem: ArrayList<Vuelo> = ArrayList()
        val repository = DaoVuelo()
        val viewModelFactory = HomeViewModelFactory(repository)
        homeViewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)
        homeViewModel.getVuelos()
        homeViewModel.vuelos.observe(viewLifecycleOwner, Observer {
                response ->
            if(response.isSuccessful){
                response.body()?.forEach{ vuelo->
                    vuelos.add(vuelo)
                    Log.d("Vuelo", vuelo.toString())
                }
            }else{
                Log.d("Response", response.code().toString())
            }
        })
        return vuelosTem
    }

    private fun showDatePickerDialog(et : EditText) {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year,et) }
        datePicker.show(getParentFragmentManager(), "Destino")
    }

    fun onDateSelected(day: Int, month: Int, year: Int,et: EditText) {
        et.setText("$day/$month/$year")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecycler() {
        binding.rvResultado.layoutManager = LinearLayoutManager(context)
        val adapter = VuelosResultAdapter(vuelos)
        binding.rvResultado.adapter = adapter
        Log.d("InitRecycler Vuelos", vuelos.toString())
    }
}