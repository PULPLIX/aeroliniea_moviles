package com.example.aerolinea.MyAsyncTask

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.aerolinea.Coroutines.CoroutinesAsyncTask
import com.example.aerolinea.Model.*
import com.example.aerolinea.Socket.Socket
import com.example.aerolinea.View.MainUserActivity
import com.example.aerolinea.View.ui.asientos.asientos_vuelo
import com.example.aerolinea.databinding.ActivityAsientosVueloBinding
import com.example.aerolinea.databinding.AlertCompraBinding
import com.example.aerolinea.util.Constans
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.wait
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.HashMap
import java.util.concurrent.TimeUnit

class AsientosAyncTask(
    private var activity: asientos_vuelo?,
    binding: ActivityAsientosVueloBinding
) : CoroutinesAsyncTask<Int, Int, String>("MysAsyncTask") {
    private var apiUrl: String = "http://10.0.2.2:8081/Backend/api/vuelos/"
    var binding = binding
    var action: String = ""
    var method: String = ""
    val asientos = ArrayList<ArrayList<String>>()
    var tiquetesVuelo = HashMap<Int, ArrayList<Int>>()
    lateinit var vuelo: Vuelo
    lateinit var dataJSON: String
    private val CONNECT_TIMEOUT = 15L
    private val READ_TIMEOUT = 15L
    private val WRITE_TIMEOUT = 15L
    private var user: Usuario? = null
    var sk = Socket()

    init {
        sk.createWebSocketClient("tiquetesSocket")
    }

    override fun doInBackground(vararg params: Int?): String {
        var result = ""
        result = processRequest()
        return result
    }

    fun setApiUrl(action: String) {
        apiUrl = "http://10.0.2.2:8081/Backend/api/vuelos/"
        this.action = action
        apiUrl += action
    }

    fun processRequest(): String {
        var result = ""
        if(method=="GET"){
            result = getRequest()
        }else if(method=="POST"){
            result = postRequest()
        }

        return result
    }
    fun postRequest():String{
        var result = ""
        try {
            val client = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()

            val body = dataJSON.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

            val request = Request.Builder()
                .url(URL(apiUrl))
                .header("Authorization", "true")
                .post(body)
                .build()

            client.newCall(request).execute().use {
                    response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                result = response.body!!.string()

            }

        }
        catch (e: IOException) {
            e.printStackTrace()
            null
        }
        return result
    }

    fun getRequest(): String {
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

    fun setAsientosOcupados(vuelo: Vuelo) {
        this.vuelo = vuelo
        apiUrl = "http://10.0.2.2:8081/Backend/api/vuelos/asientosOcupados/${vuelo.id}"
        this.method = "GET"
        this.action = "asientosOcupados"
    }

    override fun onPostExecute(result: String?) {
        if (action == "asientosOcupados") {
            Log.d("Asientos-----> ", result.toString())
            setAsientos(result)
        }else if(action == "compra"){
                sk.webSocketClient.send(result)
            irMisTiquetes()
        }
    }
    private fun irMisTiquetes(){
        val intentTiquetes = Intent(activity?.applicationContext, MainUserActivity::class.java)
        intentTiquetes.putExtra("compra", "Compra realizada correctamente")
        intentTiquetes.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity?.applicationContext?.startActivity(intentTiquetes)
    }

    private fun setAsientos(result: String?) {
        val sType = object : TypeToken<HashMap<Int, ArrayList<Int>>>() {}.type
        var listaVuelos = Gson().fromJson<HashMap<Int, ArrayList<Int>>>(result, sType)
        tiquetesVuelo = listaVuelos
        cargarAsientos()

    }

    private fun cargarAsientos() {
        var filas: Int = vuelo.avionId.cantidadFilas
        var columnas: Int = vuelo.avionId.asientosFila
        for (i in 1..filas) {
            val layout: LinearLayout = LinearLayout(activity?.applicationContext)
            layout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layout.setHorizontalGravity(Gravity.CENTER)
            layout.orientation = LinearLayout.HORIZONTAL
            for (j in 1..columnas) {
                val btn: Button = Button(activity?.applicationContext)
                btn.layoutParams = LinearLayout.LayoutParams(110, 110)
                btn.tag = i.toString() + j.toString()

                if (isTicketSold(i, j)) {
                    btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DCDC1717"))
                } else {
                    btn.setOnClickListener {
                        if (btn.backgroundTintList!!.defaultColor == -15348162) {
                            btn.backgroundTintList =
                                ColorStateList.valueOf(Color.parseColor("#3268F3"))
                            var fila = btn.tag.toString()[0]
                            var col = btn.tag.toString()[1]
                            removeAsiento(fila.toString(), col.toString())
                        } else {
                            var fila = btn.tag.toString()[0]
                            var col = btn.tag.toString()[1]
                            var cunjontoAsientos = ArrayList<String>()
                            cunjontoAsientos.add(fila.toString())
                            cunjontoAsientos.add(col.toString())
                            asientos.add(cunjontoAsientos)
                            btn.backgroundTintList =
                                ColorStateList.valueOf(Color.parseColor("#15CE3E"))
                        }
                    }
                    btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3268F3"))
                }
                layout.addView(btn)

            }
            binding.linearAsientos.addView(layout)
        }
    }

    fun removeAsiento(row: String, col: String){
        var it = 0
        var eliminarPos = 0
        for (asiento in asientos){
            if (asiento[0] == row && asiento[1] == col){
                eliminarPos = it
                break
            }
            it++
        }
        asientos.removeAt(eliminarPos)
    }
    fun isTicketSold(row: Int, col: Int): Boolean {
        var fila = tiquetesVuelo[row]
        var contained = false
        if (fila != null) {
            contained = fila.contains(col)
        }
        return contained
    }

    override fun onPreExecute() {
        //activity?.output?.text = "Tast starting.."

    }

    override fun onProgressUpdate(vararg values: Int?) {

    }

    fun comprar() {
        if (asientos.size < 1) {
            Toast.makeText(
                activity!!.applicationContext,
                "Seleccione un asiento",
                Toast.LENGTH_LONG
            ).show()
        } else {
            showAlert()
        }
    }

    fun compraExecute() {
        var taskAsientos = AsientosAyncTask(activity, binding)
        if (taskAsientos.status == Constans.Companion.Status.RUNNING) {
            taskAsientos.cancel(true)
        }
        // Lista ciudades origen y destino
        taskAsientos.vuelo = vuelo
        taskAsientos.apiUrl = "http://10.0.2.2:8081/Backend/api/tiquetes/comprar"
        taskAsientos.method = "POST"
        taskAsientos.action = "compra"
        taskAsientos.dataJSON = this.dataJSON

        taskAsientos.execute(10).wait()
    }

    fun guardarTiquetes(compraBinding: AlertCompraBinding) {
        //Obtención de las variables a mandar en el request
        var usuasrioJSON = Gson().toJson(getUser())
        var vueloID = vuelo.id.toString()
        var asientosJSON = Gson().toJson(asientos)
        var formaPago = compraBinding.spFormaPago.selectedItem.toString()

        //Integración de todas las variables en un solo objeto tipo array
        var data = ArrayList<String>()
        data.add(usuasrioJSON)
        data.add(vueloID)
        data.add(asientosJSON)
        data.add(formaPago)

        dataJSON = Gson().toJson(data)
    }

    fun getUser(): Usuario {
        val sp = activity?.applicationContext?.getSharedPreferences("key", Context.MODE_PRIVATE)
        val usuarioSession = sp?.getString("usuario", null)
        var gson = Gson()
        var user = gson.fromJson<Usuario>(usuarioSession, Usuario::class.java)
        return user
    }

    @SuppressLint("SetTextI18n")
    fun fillAlert(compraBinding: AlertCompraBinding) {
        var total =
            vuelo.rutaId.precio - (vuelo.rutaId.precio * (vuelo.rutaId.porcentajeDescuento * 0.01))
        compraBinding.tvIdVuelo.text = "Vuelo: " + vuelo.id.toString()
        compraBinding.tvOrigen.text = vuelo.rutaId.ciudadDestino.nombre
        compraBinding.tvDestino.text = vuelo.rutaId.ciudadOrigen.nombre
        compraBinding.tvFecha.text = vuelo.fecha
        compraBinding.tvDuracion.text = vuelo.duracion
        compraBinding.tvAvion.text = vuelo.avionId.marca + " - " + vuelo.avionId.tipo
        compraBinding.tvAsientos.text = asientos.toString()
        compraBinding.tvModalidad.text = getModalidad(vuelo.modalidad)
        compraBinding.tvPrecio.text = vuelo.rutaId.precio.toString()
        compraBinding.tvCantAsientos.text = asientos.size.toString()
        compraBinding.tvDescuento.text = vuelo.rutaId.porcentajeDescuento.toString()
        compraBinding.tvTotal.text = (total * asientos.size).toString()
        loadSpinner(compraBinding)
    }

    fun showAlert() {
        val layoutInflater: LayoutInflater = LayoutInflater.from(binding.root.context)
        val compraBinding = AlertCompraBinding.inflate(layoutInflater)

        fillAlert(compraBinding)

        val builder = AlertDialog.Builder(binding.root.context).setView(compraBinding.root)

        val alertDialog: AlertDialog = builder.create()
        alertDialog.getWindow()
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        compraBinding.btnCancelar.setOnClickListener {
            alertDialog.dismiss()
        }
        compraBinding.btnCompra.setOnClickListener {
            guardarTiquetes(compraBinding)
            compraExecute()
        }

        alertDialog.show()
    }

    fun getModalidad(modalidad: String): String {
        if (modalidad.equals("1")) {
            return "Ida"
        } else {
            return "Ida y vuelta"
        }
    }

    fun loadSpinner(compraBinding: AlertCompraBinding) {
        val spinner = compraBinding.spFormaPago
        if (spinner != null) {
            val adapter = ArrayAdapter(
                activity!!.applicationContext,
                android.R.layout.simple_spinner_item, listOf("Efectivo", "Tarjeta")
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
}