package com.example.aerolinea.adapters

import android.content.Intent
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.databinding.ItemVueloBinding
import com.example.aerolinea.R
import com.example.aerolinea.View.ui.asientos.asientos_vuelo
import com.example.aerolinea.login_register.Register


class VuelosResultAdapter(val vuelos: ArrayList<Vuelo>) :
    RecyclerView.Adapter<VuelosResultAdapter.VueloResultHolder>() {

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


    class VueloResultHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val viewB = ItemVueloBinding.bind(view)
        fun render(vuelo: Vuelo){
            viewB.tvDestino.text = vuelo.rutaId.ciudadDestino.nombre
            viewB.tvOrigen.text = vuelo.rutaId.ciudadOrigen.nombre
            viewB.tvFecha.text = vuelo.fecha
            viewB.tvDuracion.text = vuelo.duracion
            viewB.tvModalidad.text = getModalidad(vuelo.modalidad)
            viewB.tvPrecio.text = vuelo.rutaId.precio.toString()
            viewB.idVuelo.text = vuelo.id.toString()
            viewB.btnAsientos.setOnClickListener{inflateAsientos(vuelo)}
        }

        fun inflateAsientos(vuelo:Vuelo){
            val intent = Intent(view.context, asientos_vuelo::class.java)
            intent.putExtra("vuelo",  vuelo)
            view.context.startActivity(intent)
        }
        fun getModalidad(modalidad:String):String{
            if(modalidad.equals("1")){
                return "Ida"
            }else{
                return "Ida y vuelta"
            }
        }
    }
}