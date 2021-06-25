package com.example.aerolinea.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.aerolinea.Model.ModelTiquetes
import com.example.aerolinea.Model.Tiquete
import com.example.aerolinea.R
import com.example.aerolinea.databinding.ItemTiqueteBinding
import java.util.*
import kotlin.collections.ArrayList

class TiquetesAdapter( var tiquetes: ArrayList<Tiquete>):RecyclerView.Adapter<TiquetesAdapter.TiquetesHolder>(),Filterable {

    var listAplications: ArrayList<Tiquete> =  ArrayList(tiquetes)
    var tempArrayList = ArrayList(listAplications)
    var tempNameVersionList = ArrayList(tiquetes)

    //private var tiquetesTem = ArrayList<Tiquete>(tiquetes)

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): TiquetesHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_tiquete, parent, false)

        return TiquetesHolder(v);
    }

    override fun onBindViewHolder(holder: TiquetesAdapter.TiquetesHolder, position: Int) {
        Log.d("bindView:", position.toString())
        holder.render(tiquetes[position])
    }

    override fun getItemCount(): Int = tiquetes.size

    fun deleteItem(position: Int) {
        val tiquete = tiquetes.get(position)
        ModelTiquetes.deleteTiqueteUsuario(tiquete.id)
        tiquetes.removeAt(position)
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, tiquetes.size);
    }

    fun addItem(i: Int, tiquete: Tiquete) {
        tiquetes.add(i, tiquete)
        notifyDataSetChanged()
    }


    fun filter(text: String?) {

        val text = text!!.toLowerCase(Locale.getDefault())

        listAplications.clear()
        tiquetes.clear()

        if (text.length == 0) {
            listAplications.addAll(tempArrayList)
            tiquetes.addAll(tempNameVersionList)
        } else {
            for (i in 0..tempNameVersionList.size - 1) {
                 if (tempNameVersionList.get(i).vueloId.rutaId.ciudadOrigen.nombre!!.toLowerCase(Locale.getDefault()).contains(text)) {
                    listAplications.add(tempArrayList.get(i))
                    tiquetes.add(tempNameVersionList.get(i))
                }
            }
        }
        notifyDataSetChanged()

    }

    class TiquetesHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val viewB = ItemTiqueteBinding.bind(view)
        fun render(tiquete: Tiquete) {
            viewB.tvDestino.text = tiquete.vueloId.rutaId.ciudadDestino.nombre
            viewB.tvOrigen.text = tiquete.vueloId.rutaId.ciudadOrigen.nombre
            viewB.tvFecha.text = tiquete.vueloId.fecha
            viewB.tvDuracion.text = tiquete.vueloId.duracion
            viewB.tvModalidad.text = getModalidad(tiquete.vueloId.modalidad)
        }

        fun getModalidad(modalidad: String): String {
            if (modalidad.equals("1")) {
                return "Ida"
            } else {
                return "Ida y vuelta"
            }
        }
    }

    override fun getFilter(): Filter {
        return filter
    }
}