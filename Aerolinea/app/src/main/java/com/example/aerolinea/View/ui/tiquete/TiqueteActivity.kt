package com.example.aerolinea.View.ui.tiquete

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aerolinea.Model.Tiquete
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.databinding.ActivityAsientosVueloBinding
import com.example.aerolinea.databinding.ActivityTiqueteBinding
import com.example.aerolinea.databinding.AlertCompraBinding

class TiqueteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTiqueteBinding
    private lateinit var tiquete : Tiquete

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiqueteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.extras
        tiquete = data?.getSerializable("Tiquete") as Tiquete
        fillTicket()
    }

    @SuppressLint("SetTextI18n")
    fun fillTicket() {
        var total = tiquete.vueloId.rutaId.precio - (tiquete.vueloId.rutaId.precio * (tiquete.vueloId.rutaId.porcentajeDescuento * 0.01))
        binding.tvIdVuelo.text = "Vuelo: " + tiquete.vueloId.id.toString()
        binding.tvOrigen.text = tiquete.vueloId.rutaId.ciudadDestino.nombre
        binding.tvDestino.text = tiquete.vueloId.rutaId.ciudadOrigen.nombre
        binding.tvFecha.text = tiquete.vueloId.fecha
        binding.tvDuracion.text = tiquete.vueloId.duracion
        binding.tvAvion.text = tiquete.vueloId.avionId.marca + " - " + tiquete.vueloId.avionId.tipo
        binding.tvAsientos.text = "F: " + tiquete.filaAsisento.toString() + "- C:" + tiquete.columnaAsiento.toString()
        binding.tvModalidad.text = tiquete.vueloId.modalidad
        binding.tvPrecio.text = tiquete.vueloId.rutaId.precio.toString()
        binding.tvDescuento.text = tiquete.vueloId.rutaId.porcentajeDescuento.toString()
        binding.tvTipoPago.text = tiquete.formaPago
        binding.tvTotal.text = total.toString()
    }
}