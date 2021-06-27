package com.example.aerolinea.MyAsyncTask

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aerolinea.Coroutines.CoroutinesAsyncTask
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.View.ui.home.HomeFragment
import com.example.aerolinea.adapters.VuelosResultAdapter
import com.example.aerolinea.databinding.FragmentHomeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class VuelosAsyncTask(private var activity: HomeFragment?, binding: FragmentHomeBinding) : CoroutinesAsyncTask<Int, Int, String>("MysAsyncTask") {
    private var apiUrl: String = "http://10.0.2.2:8081/Backend/api/vuelos/"
    var binding = binding
    var action: String = ""
    var method:String  = "GET"
    var vuelos = arrayListOf<Vuelo>()
    override fun doInBackground(vararg params: Int?): String {
        var result = ""

        result = processRequest()

        Log.d("Result", result)
        return result
    }

    fun setApiUrl(action: String, method: String){
        apiUrl = "http://10.0.2.2:8081/Backend/api/vuelos/"
        this.action = action
        this.method = method

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
                        .openConnection() as HttpURLConnection //se abre una nueva aconeccion http
                if(method != "GET"){
                    val request = apiUrl
                    val url = URL(request)

                    urlConnection.setDoOutput(true) //Asegura que la peticion sera usada para el envio de datos
                    urlConnection.setInstanceFollowRedirects(false)
                    urlConnection.setRequestMethod(method)//setea el tipo de m[etodo por el que se envia la peticion
                    urlConnection.setRequestProperty("Content-Type", "application/json; utf-8")//agrega los parametros de la cabescera de la peticion para que sean de tipo json
                    urlConnection.setRequestProperty("Accept", "application/json");//configura la peticion para que acepte json
                    urlConnection.setUseCaches(false)
                    //Creacion del body del request
                    String jsonInputString = gson.toJSON()objt;
                    con.getOutputStream().use { os ->
                        val input: ByteArray = jsonInputString.getBytes("utf-8")
                        os.write(input, 0, input.size)
                    }
                    DataOutputStream(urlConnection.getOutputStream()).use { wr -> wr.write(postData) }
                }

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
            listarVuelos(result.toString())
        }
        if (action == "buscar"){
            buscar(result.toString())
        }
    }

    fun buscar(busqueda: String){

    }

    fun listarVuelos(listaVuelos: String){
        val sType = object : TypeToken<List<Vuelo>>() {}.type
        val listaVuelos = Gson().fromJson<List<Vuelo>>(listaVuelos, sType)

        listaVuelos.forEach { vuelo ->
            vuelos.add(vuelo)
        }
        val adapter = VuelosResultAdapter(vuelos)
        binding.rvResultado.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvResultado.adapter = adapter
    }
    override fun onPreExecute() {
        //activity?.output?.text = "Tast starting.."

    }

    override fun onProgressUpdate(vararg values: Int?) {

    }
}