package com.example.aerolinea.Model

import java.io.Serializable

data class Usuario(var correo:String, var nombre:String, var password: String, var celular: String, var telefono: String, var direccion: String, var rol: String): Serializable