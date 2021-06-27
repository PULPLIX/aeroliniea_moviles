package com.example.aerolinea.MyAsyncTask

import android.R
import android.util.Log
import android.widget.ArrayAdapter
import com.example.aerolinea.Coroutines.CoroutinesAsyncTask
import com.example.aerolinea.Model.Ciudad
import com.example.aerolinea.View.ui.home.HomeFragment
import com.example.aerolinea.databinding.FragmentHomeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class CiudadesAsyncTask(private var activity: HomeFragment?, binding: FragmentHomeBinding) : CoroutinesAsyncTask<Int, Int, String>("MysAsyncTask") {
    private var apiUrl: String = "http://10.0.2.2:8081/Backend/api/ciudades/"
    var binding = binding
    var action: String = ""
    override fun doInBackground(vararg params: Int?): String {
        var result = ""

        result = processRequest()

        Log.d("Result", result)
        return result
    }

    fun setApiUrl(action: String){
        apiUrl = "http://10.0.2.2:8081/Backend/api/ciudades/"

        this.action = action

        apiUrl += action
    }

    fun processRequest(): String {
        var result = ""
        try {
            val url: URL
            var urlConnection: HttpURLConnection? = null
            try {
                url = URL(apiUrl)
                urlConnection = url
                    .openConnection() as HttpURLConnection
                val `in` = urlConnection.inputStream
                val isw = InputStreamReader(`in`)
                var data = isw.read()
                while (data != -1) {
                    result += data.toChar()
                    data = isw.read()
                    print(result)
                }
                // return the data to onPostExecute method
                return result
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                urlConnection?.disconnect()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "Exception: " + e.message
        }
        return result
    }

    override fun onPostExecute(result: String?){
        if (action == "listar"){
            llenarCiudades(result.toString())
        }
    }

    fun llenarCiudades(cuidadesOrigen: String){
        val gson = Gson()
        val sType = object : TypeToken<List<Ciudad>>() { }.type
        val ciudadesOrigen = gson.fromJson<List<Ciudad>>(cuidadesOrigen, sType)
        var ciudades = ArrayList<String>()
        ciudadesOrigen.forEach { ciudad->
            ciudades.add(ciudad.nombre)
        }

        // Ciudades Origen
        var adapterOrigen =
            ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, ciudades)
        binding.etOrigen.setAdapter<ArrayAdapter<String>>(adapterOrigen)
        binding.etOrigen.adapter
        // Ciudades Destino
        var adapterDestino =
                ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, ciudades)
        binding.etDestino.setAdapter<ArrayAdapter<String>>(adapterDestino)
        binding.etDestino.adapter

    }

    override fun onPreExecute() {
        //activity?.output?.text = "Tast starting.."

    }

    override fun onProgressUpdate(vararg values: Int?) {

    }
}