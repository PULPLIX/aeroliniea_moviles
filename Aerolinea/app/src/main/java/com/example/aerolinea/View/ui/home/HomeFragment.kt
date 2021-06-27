package com.example.aerolinea.View.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aerolinea.Model.*
import com.example.aerolinea.MyAsyncTask.CiudadesAsyncTask
import com.example.aerolinea.MyAsyncTask.VuelosAsyncTask
import com.example.aerolinea.adapters.VuelosResultAdapter
import com.example.aerolinea.databinding.FragmentHomeBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.example.aerolinea.util.Constans.Companion.Status

class HomeFragment : Fragment() {

    var ciudades: ArrayList<Ciudad> = ArrayList()
    var ciudadesCodigo: ArrayList<String> = ArrayList()
    var taskCiudades: CiudadesAsyncTask? = null
    var taskVuelos: VuelosAsyncTask? = null

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
    ): View {
        _binding = null
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        vuelos.clear()
//        vuelos = ModelVuelos().getInstance().getVuelos()
        startService()
        buscarVuelos()
        showDateRange()
//        initRecycler()
        return root
    }

    fun startService() {
        if (taskCiudades?.status == Status.RUNNING) {
            taskCiudades?.cancel(true)
        }
        if (taskVuelos?.status == Status.RUNNING) {
            taskVuelos?.cancel(true)
        }

        // Lista ciudades origen y destino
        taskCiudades = CiudadesAsyncTask(this, binding)
        taskCiudades!!.setApiUrl("listar")
        taskCiudades?.execute(10)

        // Lista vuelos
        taskVuelos = VuelosAsyncTask(this, binding)
        taskVuelos!!.setApiUrl("listar", "GET", null)
        taskVuelos?.execute(10)
    }

    fun initRecycler() {
        val adapter = VuelosResultAdapter(vuelos)
        binding.rvResultado.layoutManager = LinearLayoutManager(context)
        binding.rvResultado.adapter = adapter
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
            if (taskVuelos?.status == Status.RUNNING) {
                taskVuelos?.cancel(true)
            }

            var modalidad: String = ""
            if (binding.checkModalidad.isChecked) {
                modalidad = "2"
            } else {
                modalidad = "1"
            }

            var origen: String = binding.etOrigen.text.toString()
            var destino: String = binding.etDestino.text.toString()
            ciudades = taskCiudades?.ciudadesResult as ArrayList<Ciudad>

            var codOrigen = getCiudadCodigo(origen)
            var codDestino = getCiudadCodigo(destino)

            var fechaI = binding.etSalida.text
            var fechaF = binding.etRegreso.text
            var descuento: String = "false"

            val map: HashMap<String, String> = hashMapOf(
                "Modalidad" to modalidad,
                "idOrigen" to codOrigen.toString(),
                "idDestino" to codDestino.toString(),
                "fechaI" to fechaI.toString(),
                "fechaF" to fechaF.toString(),
                "descuento" to descuento
            )

            if (validarBusqueda()) {
                taskVuelos = VuelosAsyncTask(this, binding)
                taskVuelos!!.setApiUrl("buscar", "GET", map)
                taskVuelos?.execute(10)
            }else{
                Toast.makeText(requireContext(), "No ha llenado todos los campos", Toast.LENGTH_LONG)
            }
        }
    }

    fun getCiudadCodigo(nombre: String): Int {
        ciudades.forEach { ciudad ->
            if (ciudad.nombre == nombre) {
                return ciudad.id
            }
        }
        return 0
    }

    fun validarBusqueda(): Boolean {
        var origen: Boolean = binding.etOrigen.text.isNotEmpty()
        var destino: Boolean = binding.etDestino.text.isNotEmpty()
        var fechaI = binding.etSalida.text.isNotEmpty()
        var fechaF = binding.etRegreso.text.isNotEmpty()

        return (origen && destino && fechaI && fechaF)
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