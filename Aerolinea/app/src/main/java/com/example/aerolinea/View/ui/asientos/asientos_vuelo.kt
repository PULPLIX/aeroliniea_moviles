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
import com.example.aerolinea.Socket.Socket
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
            taskAsientos!!.comprar()
        }
    }

}