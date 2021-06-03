package com.example.aerolinea.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.databinding.FragmentAsientosBinding
import com.example.aerolinea.databinding.ItemVueloBinding
import com.example.aerolinea.R
import com.example.aerolinea.View.ui.asientos.asientos


class VuelosResultAdapter(val vuelos: List<Vuelo>,val f_manager:FragmentManager) :
    RecyclerView.Adapter<VuelosResultAdapter.VueloResultHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VuelosResultAdapter.VueloResultHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VueloResultHolder(layoutInflater.inflate(R.layout.item_vuelo, parent, false),f_manager)
    }

    override fun onBindViewHolder(holder: VuelosResultAdapter.VueloResultHolder, position: Int) {
        Log.d("position:", position.toString())
        holder.render(vuelos[position])
    }

    override fun getItemCount(): Int = vuelos.size


    class VueloResultHolder(val view: View,val f_manager:FragmentManager) : RecyclerView.ViewHolder(view) {
        private val viewB = ItemVueloBinding.bind(view)
        fun render(vuelo: Vuelo){
            viewB.tvDestino.text = vuelo.rutaId.ciudadDestino.nombre
            viewB.tvOrigen.text = vuelo.rutaId.ciudadOrigen.nombre
            viewB.tvFecha.text = vuelo.fecha
            viewB.tvDuracion.text = vuelo.duracion
            viewB.tvModalidad.text = vuelo.modalidad
            viewB.tvPrecio.text = vuelo.rutaId.precio.toString()
            viewB.idVuelo.text = vuelo.id.toString()
            viewB.btnAsientos.setOnClickListener{inflateAsientos(vuelo.id)}
        }
        fun inflateAsientos(id:Int){
            val transaction = f_manager.beginTransaction()
            f_manager.beginTransaction()
                .replace(R.id.contentAsientos, asientos())
                .addToBackStack(null)
                .commit()
        }
    }
}