package com.example.aerolinea.Model

import java.io.Serializable

data class Usuario(var id: String, var contrasena: String, var nombre:String,var apellidos:String,
                   var correo:String, var fecha_Nacimiento: String, var direccion: String,
                   var telefono_Trabajo: String, var celular: String,  var rol: String): Serializable
