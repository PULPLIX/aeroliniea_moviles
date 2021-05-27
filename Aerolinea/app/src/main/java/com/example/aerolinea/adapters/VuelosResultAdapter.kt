package com.example.aerolinea.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.R
import com.example.aerolinea.databinding.ItemVueloBinding

class VuelosResultAdapter(val vuelos: List<Vuelo>):RecyclerView.Adapter<VuelosResultAdapter.VueloResultHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VuelosResultAdapter.VueloResultHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VueloResultHolder(layoutInflater.inflate(R.layout.item_vuelo, parent, false))

    }

    override fun onBindViewHolder(holder: VuelosResultAdapter.VueloResultHolder, position: Int) {
        holder.render(vuelos[position])
    }

    override fun getItemCount(): Int = vuelos.size


    class VueloResultHolder(val view: View):RecyclerView.ViewHolder(view){
        private val viewB = ItemVueloBinding.bind(view)

        fun render(vuelo: Vuelo){
            viewB.tvDestino.text = vuelo.rutaId.ciudadDestino.nombre
            viewB.tvOrigen.text = vuelo.rutaId.ciudadOrigen.nombre
            viewB.tvFecha.text = vuelo.fecha
            viewB.tvDuracion.text = vuelo.duracion
            viewB.tvModalidad.text = vuelo.modalidad
            viewB.tvPrecio.text = vuelo.rutaId.precio.toString()
        }
    }
}