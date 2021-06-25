package com.example.aerolinea.Model

import java.io.Serializable

data class Horario(
    var id: Int,
    var diaSemana: String,
    var horaLlegada: Int
): Serializable