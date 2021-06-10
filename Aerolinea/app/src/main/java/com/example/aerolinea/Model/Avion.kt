package com.example.aerolinea.Model

import java.io.Serializable

data class Avion(
    var id: Int,
    var tipo: String,
    var capacidad: Int,
    var anio: Int,
    var marca: String,
    var asientosFila: Int,
    var cantidadFilas: Int,
): Serializable