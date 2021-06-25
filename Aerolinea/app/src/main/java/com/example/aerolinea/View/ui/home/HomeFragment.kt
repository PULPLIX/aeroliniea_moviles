package com.example.aerolinea.View.ui.home

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aerolinea.Model.*
import com.example.aerolinea.View.ui.DatePickerFragment
import com.example.aerolinea.adapters.VuelosResultAdapter
import com.example.aerolinea.databinding.FragmentHomeBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    var ciudades: ArrayList<String> = ArrayList()
    var ciudadesCodigo: ArrayList<String> = ArrayList()

    //private lateinit var vuelos: ArrayList<Vuelo>
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var vuelos = arrayListOf<Vuelo>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = null
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        vuelos.clear()
        vuelos = ModelVuelos().getInstance().getVuelos()
        guardaCiudades()
        spinnerOrigen(ciudades)
        spinnerDestino(ciudades)
        buscarVuelos()
        showDateRange()
        initRecycler()
        return root
    }

    fun initRecycler() {
        val adapter = VuelosResultAdapter(vuelos)
        binding.rvResultado.layoutManager = LinearLayoutManager(context)
        binding?.rvResultado?.adapter = adapter
    }

    fun initRecyclerVuelos(vuelosBusqueda: ArrayList<Vuelo>) {
        val adapter = VuelosResultAdapter(vuelosBusqueda)
        binding.rvResultado.layoutManager = LinearLayoutManager(context)
        binding?.rvResultado?.adapter = adapter
    }

    fun spinnerOrigen(ciudadesOrigen: ArrayList<String>) {

        Log.d("Ciudades Origen", ciudades.toString())
        var adapter =
            ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, ciudadesOrigen)
        _binding?.etOrigen?.setAdapter<ArrayAdapter<String>>(adapter)
        _binding?.etOrigen?.adapter
    }

    fun spinnerDestino(ciudadesDestino: ArrayList<String>) {
        Log.d("Ciudades Destino", ciudades.toString())

        var adapter =
            ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, ciudadesDestino)
        _binding?.etDestino?.setAdapter<ArrayAdapter<String>>(adapter)
        _binding?.etDestino?.adapter
    }

//    fun listaVuelos(listaVuelos: String): ArrayList<Vuelo> {
//        var vuelosTem: ArrayList<Vuelo> = ArrayList()
//        val sType = object : TypeToken<List<Vuelo>>() {}.type
//        val listaVuelos = Gson().fromJson<List<Vuelo>>(listaVuelos, sType)
//
//        listaVuelos.forEach { vuelo ->
//            vuelos.add(vuelo)
//        }
//        Log.d("Vuelos SISI", vuelos.toString())
//        return vuelosTem
//    }


    private fun showDatePickerDialog(et: EditText) {
        val datePicker =
            DatePickerFragment { day, month, year -> onDateSelected(day, month, year, et) }
        datePicker.show(getParentFragmentManager(), "Destino")
    }

    fun onDateSelected(day: Int, month: Int, year: Int, et: EditText) {
        et.setText("$day/$month/$year")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showDateRangePicker() {
        val dateRangePichek = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Escoja las fechas")
            .build()

        dateRangePichek.show(
            this.requireFragmentManager(),
            "date_range_picker"
        )
        dateRangePichek.addOnPositiveButtonClickListener { datePicker ->
            val startDate = datePicker.first
            val endDate = datePicker.second
            if (startDate != null && endDate != null) {
                binding.etSalida.text = convertLongToDate(startDate)
                binding.etRegreso.text = convertLongToDate(endDate)
            }
        }

    }

    fun convertLongToDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "MM/dd/yyyy",
            Locale.getDefault()
        )
        return format.format(date)
    }

    @SuppressLint("NewApi")
    fun buscarVuelos() {
        binding.btnBuscar.setOnClickListener {
            val origen = binding.etOrigen.text.toString()
            val destino = binding.etDestino.text.toString()
            val salida = binding.etSalida.text.toString()
            val llegada = binding.etRegreso.text.toString()

            if(origen.toString().isNotEmpty() && destino.toString().isNotEmpty() && salida.toString().isNotEmpty() && llegada.toString().isNotEmpty()){
                initRecyclerVuelos(
                    ModelVuelos().getInstance().findVuelo(
                        getModalidad(binding.checkModalidad.isChecked),
                        origen,
                        destino,
                        salida,
                        llegada
                    )
                )
            }else{
                Toast.makeText(this.context, "Rellene todos los campos",Toast.LENGTH_LONG).show()
            }

        }
    }

    fun guardaCiudades() {
        ciudades.clear()
        ciudadesCodigo.clear()

        ciudades.add("Alajuela")
        ciudades.add("Miami")
        ciudades.add("Toronto")
        ciudades.add("NY")
        ciudades.add("Hawai")
        ciudades.add("California")
        ciudades.add("Japon")
        ciudadesCodigo.add("1")
        ciudadesCodigo.add("2")
        ciudadesCodigo.add("3")
        ciudadesCodigo.add("4")
        ciudadesCodigo.add("5")
        ciudadesCodigo.add("6")
        ciudadesCodigo.add("7")
    }

    fun getModalidad(modalidad: Boolean): String {
        if (modalidad)
            return "1"
        return "2"
    }

    fun showDateRange() {
        binding.btnShowRangePicker.setOnClickListener {
            showDateRangePicker()
        }
    }
}