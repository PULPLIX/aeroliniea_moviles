package com.example.aerolinea.Model

data class Avion(
    var id: Int,
    var tipo: String,
    var capacidad: Int,
    var anio: Int,
    var marca: String,
    var asientosFila: Int,
    var cantidadFilas: Int,
)