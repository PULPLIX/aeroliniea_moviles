package com.example.aerolinea.MyAsyncTask

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aerolinea.Coroutines.CoroutinesAsyncTask
import com.example.aerolinea.Model.Tiquete
import com.example.aerolinea.Model.Usuario
import com.example.aerolinea.View.ui.gallery.GalleryFragment
import com.example.aerolinea.adapters.TiquetesAdapter
import com.example.aerolinea.databinding.FragmentGalleryBinding
import com.example.aerolinea.databinding.FragmentHomeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class TiquetesAsycTask(private var activity: GalleryFragment?, binding: FragmentGalleryBinding) :
    CoroutinesAsyncTask<Int, Int, String>("TiquetesAsyncTask") {
    private var apiUrl: String = "http://10.0.2.2:8081/Backend/api/usuario"
    var binding = binding
    var action: String = ""
    val progresDialog = ProgressDialog(activity?.context)
    var method: String = ""
    val usario = getUser()


    lateinit var tiquetes: List<Tiquete>

    override fun doInBackground(vararg params: Int?): String {
        var result = ""
        result = processRequest()
        Log.d("Result", result)
        return result
    }

    fun setApiUrl(action: String, method: String, parameters: HashMap<String,String>?){
        apiUrl = "http://10.0.2.2:8081/Backend/api/usuario/"
        this.action = action
        this.method = method
        apiUrl += action
        addParamsToUrl(parameters)
        Log.d("TIQUETES", apiUrl)

    }

    fun addParamsToUrl(parameters: HashMap<String,String>?){
        if (parameters != null) {
            apiUrl += "/"
            for ((key, value) in parameters) {
                apiUrl +="$value"
            }
        }
    }

    fun getUser(): Usuario {
        val sp = activity?.context?.getSharedPreferences("key", Context.MODE_PRIVATE)
        val usuarioSession = sp?.getString("usuario",null)
        var gson = Gson()
        var user = gson.fromJson<Usuario>(usuarioSession, Usuario::class.java)
        return user
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

    override fun onPostExecute(result: String?) {
        if (progresDialog.isShowing) progresDialog.dismiss()
//        /tiquetesUsuario/{id}
        Log.d("RESULTADO DE CONSULTA", result.toString())

        if (action == "tiquetesUsuario") {
            listarTiquetes(result.toString())
        }
        if (action == "buscar") {
//            listarVuelos(result.toString())
            print(result.toString())
        }
    }

    fun listarTiquetes(tiquetesResult: String){

        val sType = object : TypeToken<ArrayList<Tiquete>>() {}.type
        var listaTiquetes = Gson().fromJson<ArrayList<Tiquete>>(tiquetesResult, sType)

        if(listaTiquetes == null){
            listaTiquetes = ArrayList<Tiquete>()
        }

        val adapter = TiquetesAdapter(listaTiquetes)
        binding.rvTiquetes.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvTiquetes.adapter = adapter
    }


    override fun onPreExecute() {
        //activity?.output?.text = "Tast starting.."
//        progresDialog.setMessage("Cargando tiquetes ...")
//        progresDialog.setCancelable(false)
//        progresDialog.show()
    }

    override fun onProgressUpdate(vararg values: Int?) {

    }
}