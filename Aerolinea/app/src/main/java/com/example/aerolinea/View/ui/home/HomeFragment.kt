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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aerolinea.Coroutines.CoroutinesAsyncTask
import com.example.aerolinea.Daos.DaoCiudad
import com.example.aerolinea.Daos.DaoVuelo
import com.example.aerolinea.Model.Ciudad
import com.example.aerolinea.Model.Tiquete
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.View.ui.DatePickerFragment
import com.example.aerolinea.ViewModel.HomeViewModel
import com.example.aerolinea.ViewModel.MainViewModel
import com.example.aerolinea.ViewModelFactory.HomeViewModelFactory
import com.example.aerolinea.ViewModelFactory.MainViewModelFactory
import com.example.aerolinea.adapters.VuelosResultAdapter
import com.example.aerolinea.databinding.FragmentHomeBinding
import com.example.aerolinea.util.Constans
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import com.example.aerolinea.util.Constans.Companion.Status
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.coroutineContext


class HomeFragment : Fragment() {


    var ciudades: ArrayList<String> = ArrayList()
    var ciudadesCodigo: ArrayList<String> = ArrayList()
    var task: MyAsyncTask? = null

    private lateinit var mainViewModel: MainViewModel
    private lateinit var homeViewModel: HomeViewModel
    //private lateinit var vuelos: ArrayList<Vuelo>
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
        initRecycler()

        //binding.etSalida.setOnClickListener { showDatePickerDialog(binding.etSalida) }
        //binding.etRegreso.setOnClickListener { showDatePickerDialog(binding.etRegreso) }
        showDateRange()
        startService()
        startBuscar()
        return root
    }


    fun startService() {
        if (task?.status == Status.RUNNING){
            task?.cancel(true)
        }
        task = MyAsyncTask(_binding,this.requireContext())
        task?.execute(10)
    }

    fun startBuscar() {
        binding.btnBuscar.setOnClickListener {
            if (task?.status == Status.RUNNING) {
                task?.cancel(true)
            }
            task = MyAsyncTask(_binding, this.requireContext())
            task?.executeListar(10)
        }
    }

    class MyAsyncTask(private var activity: FragmentHomeBinding?, private var context: Context) : CoroutinesAsyncTask<Int, Int, String>("MysAsyncTask") {
        private var apiUrl: String = "https://c4f6263c2e5b.ngrok.io/Backend/api"
        var ciudades: ArrayList<String> = ArrayList()
        var ciudadesCodigo: ArrayList<String> = ArrayList()
        var vuelos: ArrayList<Vuelo> = ArrayList()

        override fun doInBackground(vararg params: Int?): String {
            var current = ""
            var URLAPI : String = apiUrl + "/ciudades/listar"
            try {
                val url: URL
                var urlConnection: HttpURLConnection? = null
                try {
                    url = URL(URLAPI)
                    urlConnection = url
                            .openConnection() as HttpURLConnection
                    val `in` = urlConnection.inputStream
                    val isw = InputStreamReader(`in`)
                    var data = isw.read()
                    while (data != -1) {
                        current += data.toChar()
                        data = isw.read()
                    }
                    // return the data to onPostExecute method
                    return current
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    urlConnection?.disconnect()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return "Exception: " + e.message
            }
            Log.d("Current", current)
            return current
        }

        override fun onPostExecute(result: String?) {
//            activity?.progressBar?.visibility = View.GONE
            //activity?.resultado?.text = result.toString()
            spinnerOrigen(result.toString())
            spinnerDestino(result.toString())
        }

        override fun onPostExecuteListar(result: String?) {
//            activity?.progressBar?.visibility = View.GONE
            //activity?.resultado?.text = result.toString()
            listaVuelos(result.toString())
            initRecycler()
        }

        override fun onPreExecute() {
            /*
            activity?.output?.text = "Tast starting.."
            activity?.progressBar?.visibility = View.VISIBLE
            activity?.progressBar?.max = 10
            activity?.progressBar?.progress = 0
             */
        }

        override fun onProgressUpdate(vararg values: Int?) {

        }

        fun spinnerOrigen(ciudadesOrigen: String){

            val sType = object : TypeToken<List<Ciudad>>() { }.type
            val listaCiudades = Gson().fromJson<List<Ciudad>>(ciudadesOrigen, sType)

            listaCiudades.forEach { ciudad ->
                ciudades.add(ciudad.nombre)
                ciudadesCodigo.add(ciudad.id.toString())
            }
            Log.d("Ciudades Origen", ciudades.toString())
            var adapter = ArrayAdapter( context, R.layout.simple_spinner_item, ciudades)
            activity?.etOrigen?.setAdapter<ArrayAdapter<String>>(adapter)
            activity?.etOrigen?.adapter
        }

        fun spinnerDestino(ciudadesDestino: String){
            ciudades.clear()

            val sType = object : TypeToken<List<Ciudad>>() { }.type
            val listaCiudades = Gson().fromJson<List<Ciudad>>(ciudadesDestino, sType)

            listaCiudades.forEach { ciudad ->
                ciudades.add(ciudad.nombre)
                ciudadesCodigo.add(ciudad.id.toString())
            }
            Log.d("Ciudades Destino", ciudades.toString())

            var adapter = ArrayAdapter(context, R.layout.simple_spinner_item, ciudades)
            activity?.etDestino?.setAdapter<ArrayAdapter<String>>(adapter)
            activity?.etDestino?.adapter
        }

        override fun doInBackgroundListarVuelos(vararg params: Int?): String {
            var current = ""
            var URLAPI : String = apiUrl + "/vuelos/listar"
            try {
                val url: URL
                var urlConnection: HttpURLConnection? = null
                try {
                    url = URL(URLAPI)
                    urlConnection = url
                            .openConnection() as HttpURLConnection
                    val `in` = urlConnection.inputStream
                    val isw = InputStreamReader(`in`)
                    var data = isw.read()
                    while (data != -1) {
                        current += data.toChar()
                        data = isw.read()
                    }
                    // return the data to onPostExecute method
                    return current
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    urlConnection?.disconnect()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return "Exception: " + e.message
            }
            Log.d("Current", current)
            return current


            var modalidad: String = ""
            if ( activity?.checkModalidad!!.isChecked){
                modalidad = "2"
            }else{
                modalidad = "1"
            }

            var origen: String =  activity?.etOrigen?.text.toString()
            var destino: String =  activity?.etDestino?.text.toString()
            var codOrigen = ciudadesCodigo.get(ciudades.indexOf(origen))
            var codDestino = ciudadesCodigo.get(ciudades.indexOf(destino))

            var fechaI =  activity?.etSalida?.text
            var fechaF =  activity?.etRegreso?.text
            var descuento: String = "false"
            var vuelosTem: ArrayList<Vuelo> = ArrayList()
        }

        fun listaVuelos(listaVuelos: String): ArrayList<Vuelo>{
            var vuelosTem: ArrayList<Vuelo> = ArrayList()
            val sType = object : TypeToken<List<Vuelo>>() { }.type
            val listaVuelos = Gson().fromJson<List<Vuelo>>(listaVuelos, sType)

            listaVuelos.forEach { vuelo ->
                vuelos.add(vuelo)
            }
            Log.d("Vuelos SISI", vuelos.toString())
            return vuelosTem
        }
        fun initRecycler() {
            activity?.rvResultado?.layoutManager = LinearLayoutManager(context)
            val adapter = VuelosResultAdapter(vuelos)
            activity?.rvResultado?.adapter = adapter
        }

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
//        binding.rvResultado.layoutManager = LinearLayoutManager(context)
//        val adapter = VuelosResultAdapter(vuelos)
//        binding.rvResultado.adapter = adapter
    }

    fun buscarVuelosASNG(){
        binding.btnBuscar.setOnClickListener{
            startService()
        }
    }

    fun showDateRangePicker(){
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

    fun convertLongToDate(time:Long): String{
        val date = Date(time)
        val format = SimpleDateFormat(
                "MM/dd/yyyy",
                Locale.getDefault()
        )
        return format.format(date)
    }
    fun showDateRange(){
        binding.btnShowRangePicker.setOnClickListener{
            showDateRangePicker()
        }
    }


}