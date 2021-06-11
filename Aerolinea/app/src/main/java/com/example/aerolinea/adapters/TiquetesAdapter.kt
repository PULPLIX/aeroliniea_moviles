package com.example.aerolinea.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aerolinea.Model.Tiquete
import com.example.aerolinea.databinding.ItemTiqueteBinding
import com.example.aerolinea.R

class TiquetesAdapter(val tiquetes: ArrayList<Tiquete>):RecyclerView.Adapter<TiquetesAdapter.TiquetesHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TiquetesHolder {
        Log.d("createViewHolder:", "HA")
        val layoutInflater = LayoutInflater.from(parent.context)
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_tiquete, parent, false)

        return  TiquetesHolder(v);
    }

    override fun onBindViewHolder(holder: TiquetesAdapter.TiquetesHolder, position: Int) {
        Log.d("bindView:",position.toString())
        holder.render(tiquetes[position])
    }

    override fun getItemCount(): Int = tiquetes.size

    fun deleteItem(position : Int){
        tiquetes.removeAt(position)
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,tiquetes.size);
    }

    fun addItem(i : Int, tiquete: Tiquete){
        tiquetes.add(i, tiquete)
        notifyDataSetChanged()
    }

    class TiquetesHolder(val view: View):RecyclerView.ViewHolder(view){
        private val viewB = ItemTiqueteBinding.bind(view)
        fun render(tiquete: Tiquete){
            Log.d("tiquete:", tiquete.toString())
            viewB.tvDestino.text = tiquete.vueloId.rutaId.ciudadDestino.nombre
            viewB.tvOrigen.text = tiquete.vueloId.rutaId.ciudadOrigen.nombre
            viewB.tvFecha.text = tiquete.vueloId.fecha
            viewB.tvDuracion.text = tiquete.vueloId.duracion
            viewB.tvModalidad.text = getModalidad(tiquete.vueloId.modalidad)
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