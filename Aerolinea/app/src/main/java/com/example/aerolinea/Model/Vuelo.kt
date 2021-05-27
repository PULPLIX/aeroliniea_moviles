package com.example.aerolinea.Model

import java.util.*

data class Vuelo(
    var id: Int,
    var modalidad: String,
    var duracion: String,
    var fecha: String,
    var avionId: Avion,
    var rutaId: Ruta,
    var tiquetesCollection: List<String>
)
