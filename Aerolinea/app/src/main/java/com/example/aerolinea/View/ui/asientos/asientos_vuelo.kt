package com.example.aerolinea.View.ui.asientos

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
import androidx.fragment.app.FragmentTransaction
import com.example.aerolinea.Model.ModelTiquetes
import com.example.aerolinea.Model.Tiquete
import com.example.aerolinea.Model.Usuario
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.View.MainUserActivity
import com.example.aerolinea.databinding.ActivityAsientosVueloBinding
import com.example.aerolinea.databinding.AlertCompraBinding
import com.example.aerolinea.R
import com.example.aerolinea.View.ui.gallery.GalleryFragment
import com.google.gson.Gson

class asientos_vuelo : AppCompatActivity() {

    private lateinit var binding: ActivityAsientosVueloBinding
    val asientos = mutableListOf<String>()
    lateinit var vuelo: Vuelo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        binding = ActivityAsientosVueloBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.extras
        vuelo = data?.getSerializable("vuelo") as Vuelo
        cargarAsientos(vuelo.avionId.cantidadFilas, vuelo.avionId.asientosFila)
        evtCompra()
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
            Log.d("Fila: ", asiento[1].toString().toInt().toString())

            Toast.makeText(applicationContext, fila.toString(), Toast.LENGTH_SHORT).show()
            val tiquete:Tiquete = Tiquete(ModelTiquetes().getInstance().getAutoIncrement(),usuario,vuelo,total,fila.toInt(),columna.toInt(),compraBinding.spFormaPago.selectedItem.toString())
            ModelTiquetes().getInstance().addTiquete(tiquete)

        }
    }
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
        compraBinding.tvModalidad.text = vuelo.modalidad
        compraBinding.tvPrecio.text = vuelo.rutaId.precio.toString()
        compraBinding.tvCantAsientos.text = asientos.size.toString()
        compraBinding.tvDescuento.text = vuelo.rutaId.porcentajeDescuento.toString()
        compraBinding.tvTotal.text = total.toString()
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

    private fun cargarAsientos(filas: Int, columnas: Int) {

        for (i in 1..filas) {
            val layout: LinearLayout = LinearLayout(applicationContext)
            layout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layout.setHorizontalGravity(Gravity.CENTER)
            layout.orientation = LinearLayout.HORIZONTAL
            for (j in 1..columnas) {
                val btn: Button = Button(applicationContext)
                btn.layoutParams = LinearLayout.LayoutParams(110, 110)
                btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3268F3"))
                btn.tag = i.toString() + j.toString()
                layout.addView(btn)
                btn.setOnClickListener {
//                    showAlert(btn.backgroundTintList!!.toString()+ btn.backgroundTintList!!.defaultColor.toString())
                    if (btn.backgroundTintList!!.defaultColor == -15348162) {
                        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3268F3"))
                        asientos.remove(btn.tag.toString())
                    } else {
                        asientos.add(btn.tag.toString());
                        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#15CE3E"))
                    }
                }
            }
            binding.linearAsientos.addView(layout)
        }
    }
}