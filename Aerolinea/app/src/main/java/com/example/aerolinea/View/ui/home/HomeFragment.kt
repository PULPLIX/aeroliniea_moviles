package com.example.aerolinea.View.ui.home

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
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
    val avion = Avion(1, "Comercial", 34, 2020, "Boing", 9, 34)
    val ny = Ciudad(1, "NY")
    val aj = Ciudad(2, "Alajuela")
    val horario = Horario(2, "Lunes", 3)
    val ruta = Ruta(1, 200.0, 20.0, ny, aj, horario)
    val vuelos = listOf<Vuelo>(
        Vuelo(1, "ida y vuelta", "2", "Mar 20, 2021", avion, ruta, listOf<Tiquete>()),
        Vuelo(1, "ida y vuelta", "2", "Mar 20, 2021", avion, ruta, listOf<Tiquete>()),
        Vuelo(1, "ida y vuelta", "2", "Mar 20, 2021", avion, ruta, listOf<Tiquete>()),
        Vuelo(1, "ida y vuelta", "2", "Mar 20, 2021", avion, ruta, listOf<Tiquete>()),
        Vuelo(1, "ida y vuelta", "2", "Mar 20, 2021", avion, ruta, listOf<Tiquete>()),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //binding.etSalida.setOnClickListener { showDatePickerDialog(binding.etSalida) }
        //binding.etRegreso.setOnClickListener { showDatePickerDialog(binding.etRegreso) }
        initRecycler()
        return root
    }

    fun initRecycler() {
        val adapter = VuelosResultAdapter(vuelos)

        binding.rvResultado.layoutManager = LinearLayoutManager(context)
        binding?.rvResultado?.adapter = adapter
//        val touchHelper = ItemTouchHelper(swipeGesture)
//        touchHelper.attachToRecyclerView(binding.rvTiquetes)

    }
    fun spinnerOrigen(ciudadesOrigen: String) {

        val sType = object : TypeToken<List<Ciudad>>() {}.type
        val listaCiudades = Gson().fromJson<List<Ciudad>>(ciudadesOrigen, sType)

        listaCiudades.forEach { ciudad ->
            ciudades.add(ciudad.nombre)
            ciudadesCodigo.add(ciudad.id.toString())
        }
        Log.d("Ciudades Origen", ciudades.toString())
        var adapter = ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, ciudades)
        _binding?.etOrigen?.setAdapter<ArrayAdapter<String>>(adapter)
        _binding?.etOrigen?.adapter
    }

    fun spinnerDestino(ciudadesDestino: String) {
        ciudades.clear()

        val sType = object : TypeToken<List<Ciudad>>() {}.type
        val listaCiudades = Gson().fromJson<List<Ciudad>>(ciudadesDestino, sType)

        listaCiudades.forEach { ciudad ->
            ciudades.add(ciudad.nombre)
            ciudadesCodigo.add(ciudad.id.toString())
        }
        Log.d("Ciudades Destino", ciudades.toString())

        var adapter = ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, ciudades)
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

//    fun showDateRange() {
//        binding.root.btnShowRangePicker.setOnClickListener {
//            showDateRangePicker()
//        }
//    }
}