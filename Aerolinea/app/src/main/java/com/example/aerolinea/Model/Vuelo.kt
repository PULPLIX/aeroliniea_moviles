package com.example.aerolinea.Model

import android.os.Parcelable
import java.io.Serializable
import java.util.*

data class Vuelo (
    var id: Int,
    var modalidad: String,
    var duracion: String,
    var fecha: String,
    var avionId: Avion,
    var rutaId: Ruta,
    var tiquetesCollection: List<Tiquete>
): Serializable