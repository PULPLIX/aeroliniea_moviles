package com.example.aerolinea.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aerolinea.Model.Tiquete
import com.example.aerolinea.databinding.ItemTiqueteBinding
import com.example.aerolinea.R

class TiquetesAdapter(val tiquetes: MutableList<Tiquete>):RecyclerView.Adapter<TiquetesAdapter.TiquetesHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TiquetesHolder {
        Log.d("createViewHolder:", "SIRVA PUTO")
        val layoutInflater = LayoutInflater.from(parent.context)
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tiquete, parent, false)

        return  TiquetesHolder(v);
    }

    override fun onBindViewHolder(holder: TiquetesAdapter.TiquetesHolder, position: Int) {
        Log.d("bindView:",position.toString())
        holder.render(tiquetes[position])
    }

    override fun getItemCount(): Int = tiquetes.size

    fun deleteItem(i : Int){
        tiquetes.removeAt(i)
        notifyDataSetChanged()
    }
    fun addItem(i : Int, tiquete: Tiquete){
        tiquetes.add(i, tiquete)
        notifyDataSetChanged()
    }

    class TiquetesHolder(val view: View):RecyclerView.ViewHolder(view){
        private val viewB = ItemTiqueteBinding.bind(view)
        fun render(tiquete: Tiquete){
            Log.d("tiquete:", tiquete.toString())
            viewB.tvDestino.text = tiquete.ciudadDestinno
            viewB.tvOrigen.text = tiquete.ciudadOrigen
            viewB.tvFecha.text = tiquete.fecha
            viewB.tvDuracion.text = tiquete.duracion
            viewB.tvModalidad.text = tiquete.modalidad
        }
    }


}