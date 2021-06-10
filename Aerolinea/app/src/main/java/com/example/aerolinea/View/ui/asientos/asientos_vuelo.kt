package com.example.aerolinea.View.ui.asientos

import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.R
import com.example.aerolinea.databinding.ActivityAsientosVueloBinding


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
        binding.btnCompra.setOnClickListener(){
            showAlert(vuelo.toString() + asientos.toString())
        }
    }

    fun showAlert(mensaje: String) {
        //set title for alert dialog
//        builder.setTitle("Compra de tiquetes")
        //set message for alert dialog
//        builder.setMessage(mensaje)
//        builder.setIcon(android.R.drawable.ic_dialog_alert)
//
//        //performing positive action
//        builder.setPositiveButton("Comprar") { dialogInterface, which ->
//            Toast.makeText(applicationContext, "clicked yes", Toast.LENGTH_LONG).show()
//        }
//        //performing cancel action
//        builder.setNeutralButton("Cancel") { dialogInterface, which ->
//            Toast.makeText(
//                applicationContext,
//                "clicked cancel\n operation cancel",
//                Toast.LENGTH_LONG
//            ).show()
//        }

        val layoutInflater:LayoutInflater = LayoutInflater.from(this)
        val view:View = layoutInflater.inflate(R.layout.alert_compra,null)
        val builder = AlertDialog.Builder(this).setView(view)

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

//        alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL)
//            .setTextColor(ContextCompat.getColor(applicationContext, R.color.rojito))
//        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
//            .setTextColor(ContextCompat.getColor(applicationContext, R.color.azulitoSecundary))

        // Set other dialog properties
        alertDialog.setCancelable(false)
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