package com.example.aerolinea.View.ui.asientos

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aerolinea.Model.ModelTiquetes
import com.example.aerolinea.Model.Tiquete
import com.example.aerolinea.Model.Usuario
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.MyAsyncTask.AsientosAyncTask
import com.example.aerolinea.MyAsyncTask.TiquetesAsyncTask
import com.example.aerolinea.View.MainUserActivity
import com.example.aerolinea.databinding.ActivityAsientosVueloBinding
import com.example.aerolinea.databinding.AlertCompraBinding
import com.example.aerolinea.R
import com.example.aerolinea.util.Constans
import com.google.gson.Gson
import java.util.HashMap

class asientos_vuelo : AppCompatActivity() {

    private lateinit var binding: ActivityAsientosVueloBinding
    val asientos = mutableListOf<String>()
    var tiquetesVuelo = mutableListOf<Tiquete>()
    lateinit var vuelo: Vuelo
    var taskAsientos: AsientosAyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        binding = ActivityAsientosVueloBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.extras
        vuelo = data?.getSerializable("vuelo") as Vuelo
        tiquetesVuelo = ModelTiquetes.getTiquetesVuelo(vuelo.id)
        startService()
        evtCompra()
    }

    fun startService() {
        if (taskAsientos?.status == Constans.Companion.Status.RUNNING) {
            taskAsientos?.cancel(true)
        }
        // Lista ciudades origen y destino
        taskAsientos = AsientosAyncTask(this, binding)
        taskAsientos!!.setAsientosOcupados(vuelo)
        taskAsientos?.execute(10)

    }

    fun evtCompra() {
        binding.btnCompra.setOnClickListener() {
            if(asientos.size <= 0){
                Toast.makeText(applicationContext, "Seleccione un asiento", Toast.LENGTH_LONG).show()
            }else{
                showAlert(vuelo.toString() + asientos.toString())
            }
        }
    }

    fun showAlert(mensaje: String) {
        val layoutInflater: LayoutInflater = LayoutInflater.from(this)
        val compraBinding = AlertCompraBinding.inflate(layoutInflater)

        fillAlert(compraBinding)

        val builder = AlertDialog.Builder(this).setView(compraBinding.root)

        val alertDialog: AlertDialog = builder.create()
        alertDialog.getWindow()
            ?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT));

        compraBinding.btnCancelar.setOnClickListener {
            alertDialog.dismiss()
        }
        compraBinding.btnCompra.setOnClickListener {
            val intentTiquetes = Intent(this, MainUserActivity::class.java)
            guardarTiquetes(compraBinding)
            intentTiquetes.putExtra("compra",  "Compra realizada correctamente")
            intentTiquetes.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            applicationContext.startActivity(intentTiquetes)
        }
        alertDialog.show()

    }
     fun guardarTiquetes(compraBinding: AlertCompraBinding) {
         var tiquetes = ModelTiquetes().getInstance().getTiquetes()
        for (asiento in asientos) {
            val sp = getSharedPreferences("key", Context.MODE_PRIVATE)
            val usuarioSession = sp.getString("usuario",null)
            var gson = Gson()
            var usuario = gson.fromJson<Usuario>(usuarioSession,Usuario::class.java)
            var total =
                vuelo.rutaId.precio - (vuelo.rutaId.precio * (vuelo.rutaId.porcentajeDescuento * 0.01))
            val fila = asiento[1].toString()
            val columna = asiento[0].toString()
            val tiquete:Tiquete = Tiquete(ModelTiquetes().getInstance().getAutoIncrement(),usuario,vuelo,total,fila.toInt(),columna.toInt(),compraBinding.spFormaPago.selectedItem.toString())
            ModelTiquetes().getInstance().addTiquete(tiquete)
        }
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
        compraBinding.tvTotal.text = (total*asientos.size).toString()
        loadSpinner(compraBinding)
    }

    fun loadSpinner(compraBinding: AlertCompraBinding) {
        val spinner = compraBinding.spFormaPago
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
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




    fun getModalidad(modalidad:String):String{
        if(modalidad.equals("1")){
            return "Ida"
        }else{
            return "Ida y vuelta"
        }
    }

    fun isTicketSold(fila: Int, columna: Int): Boolean {
        for (tiquete in tiquetesVuelo){
            if(tiquete.filaAsisento == fila && tiquete.columnaAsiento == columna){
                return true
            }
        }
        return false
    }

}