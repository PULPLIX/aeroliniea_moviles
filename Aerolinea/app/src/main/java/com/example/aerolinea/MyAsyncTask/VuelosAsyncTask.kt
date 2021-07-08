package com.example.aerolinea.MyAsyncTask

import android.app.ProgressDialog
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aerolinea.Coroutines.CoroutinesAsyncTask
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.Socket.Socket
import com.example.aerolinea.View.ui.home.HomeFragment
import com.example.aerolinea.adapters.VuelosResultAdapter
import com.example.aerolinea.databinding.FragmentHomeBinding
import com.example.aerolinea.util.Constans.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.internal.notify
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class VuelosAsyncTask(private var activity: HomeFragment?, binding: FragmentHomeBinding) : CoroutinesAsyncTask<Int, Int, String>("MysAsyncTask") {
    private var apiUrl: String = "$BASE_URL/vuelos/"
    var binding = binding
    var action: String = ""
    var method:String  = "GET"
    lateinit var  vuelo: Vuelo
    var vuelos = arrayListOf<Vuelo>()
    var parameters =  hashMapOf<String, String?>()
    var gson = Gson()


    override fun doInBackground(vararg params: Int?): String {
        var result = ""
        result = processRequest()
        return result
    }

    fun setApiUrl(action: String, method: String, parameters: HashMap<String,String>?){
        apiUrl = "$BASE_URL/vuelos/"
        this.action = action
        this.method = method
        apiUrl += action
        addParamsToUrl(parameters)

    }


    fun addParamsToUrl(parameters: HashMap<String,String>?){
        if (parameters != null) {
            apiUrl += "?"
            for ((key, value) in parameters) {
                apiUrl +="$key=$value&"
            }
        }
    }

    fun processRequest(): String {
        var result = ""
        try {
            val url: URL
            var urlConnection: HttpURLConnection? = null
            try {
                url = URL(apiUrl)
                urlConnection = url
                        .openConnection() as HttpURLConnection //se abre una nueva coneccion http
                if(method != "GET"){
                    result = postRequest(urlConnection)
                }else{
                    result = readData(urlConnection)
                }
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

    fun readData(urlConnection: HttpURLConnection) : String{
        var result:String = ""
        val `in` = urlConnection.inputStream
        val isw = InputStreamReader(`in`)
        var data = isw.read()
        while (data != -1) {
            result += data.toChar()
            data = isw.read()
        }
        return result
    }

    fun postRequest(urlConnection: HttpURLConnection): String{
        val request = apiUrl
        val url = URL(request)
        urlConnection.setDoOutput(true) //Asegura que la peticion sera usada para el envio de datos
        urlConnection.setInstanceFollowRedirects(false)
        urlConnection.setRequestMethod(method)//setea el tipo de m[etodo por el que se envia la peticion
        urlConnection.setRequestProperty("Content-Type", "application/json; utf-8")//agrega los parametros de la cabescera de la peticion para que sean de tipo json
        urlConnection.setRequestProperty("Accept", "application/json") //configura la peticion para que acepte json
        urlConnection.setUseCaches(false)//Para no usar cache
        //Creacion del body del request
        var jsonInputString = gson.toJson(vuelo)
        urlConnection.getOutputStream().use { os ->
            val input: ByteArray = jsonInputString.toByteArray(charset("UTF-8"))
            os.write(input, 0, input.size)
        }
        BufferedReader(
            InputStreamReader(urlConnection.getInputStream(), "utf-8")
        ).use { br ->
            val response = StringBuilder()
            var responseLine: String? = null
            while (br.readLine().also { responseLine = it } != null) {
                response.append(responseLine!!.trim { it <= ' ' })
            }
            println(response.toString())
            return response.toString()
        }
    }

    override fun onPostExecute(result: String?){
        if (progresDialog.isShowing){
            progresDialog.dismiss()
        }

        if (action == "listar"){
            listarVuelos(result.toString())
        }
        if (action == "buscar"){
            listarVuelos(result.toString())
            print(result.toString())
        }
        if (action == "eliminar"){
            listarVuelos(result.toString())
            print(result.toString())
        }

    }

    fun eliminarVuelo(id: String) {

    }

    fun listarVuelos(listaVuelos: String){
        val sType = object : TypeToken<ArrayList<Vuelo>>() {}.type
        var listaVuelos = Gson().fromJson<ArrayList<Vuelo>>(listaVuelos, sType)
        if(listaVuelos == null){
            listaVuelos = ArrayList<Vuelo>()
        }
        listaVuelos.forEach { vuelo ->
            vuelos.add(vuelo)
        }
        val adapter = VuelosResultAdapter(vuelos)
        adapter.notifyDataSetChanged()
        binding.rvResultado.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvResultado.adapter = adapter
    }

    val progresDialog = ProgressDialog(binding.root.context)
    override fun onPreExecute() {
        progresDialog.setMessage("Cargando vuelos...")
        progresDialog.setCancelable(false)
        progresDialog.show()
    }

    override fun onProgressUpdate(vararg values: Int?) {

    }
}