package com.example.aerolinea.Model

data class Tiquete(
    var id: Int,
    var usuarioId: Usuario,
    var vueloId: Vuelo,
    var precioFinal: Double,
    var filaAsisento: Int,
    var columnaAsiento: Int,
    var formaPago: String
)

