package com.example.aerolinea.Model

import java.io.Serializable

data class Ruta(
    var id: Int,
    var precio: Double,
    var porcentajeDescuento: Double,
    var ciudadOrigen: Ciudad,
    var ciudadDestino: Ciudad,
    var horarioId: Horario
): Serializable
