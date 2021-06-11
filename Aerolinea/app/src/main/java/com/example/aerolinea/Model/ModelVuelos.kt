package com.example.aerolinea.Model

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


//fun main(args: Array<String>) {
//    var a = ModelVuelos()
//    // Aqui se genera la clase
//}

class ModelVuelos {
    companion object SingletonVuelos {
        private var vuelos: ArrayList<Vuelo> = ArrayList()
        private var ai: Int = 0

        init {
            val avion = Avion(1, "Comercial", 34, 2020, "Boing", 9, 34)
            val ny = Ciudad(4, "NY")
            val aj = Ciudad(1, "Alajuela")
            val horario = Horario(2, "Lunes", 3)
            val ruta = Ruta(1, 200.0, 20.0, ny, aj, horario)

            val avion2 = Avion(2, "Comercial", 34, 2021, "Boing 377", 9, 34)
            val ny2 = Ciudad(6, "California")
            val aj2 = Ciudad(7, "Japon")
            val horario2 = Horario(3, "Martes", 5)
            val ruta2 = Ruta(2, 150.0, 0.0, ny2, aj2, horario2)

            addVuelo(
                Vuelo(
                    1,
                    "1",
                    "2",
                    "06/10/2021",
                    avion,
                    ruta,
                    listOf<Tiquete>()
                )
            )
            addVuelo(
                Vuelo(
                    2,
                    "2",
                    "2",
                    "06/20/2021",
                    avion,
                    ruta2,
                    listOf<Tiquete>()
                )
            )
            addVuelo(
                Vuelo(
                    3,
                    "2",
                    "2",
                    "06/22/2021",
                    avion,
                    ruta,
                    listOf<Tiquete>()
                )
            )
            addVuelo(
                Vuelo(
                    4,
                    "1",
                    "2",
                    "06/29/2021",
                    avion,
                    ruta2,
                    listOf<Tiquete>()
                )
            )
        }

        fun addVuelo(vuelo: Vuelo): Boolean {
            try {
                vuelos.add(vuelo)
                ai++
            } catch (e: Exception) {
                throw e
            } finally {
                return true
            }
        }

        fun findVuelo(key: Int): Vuelo? {
            return vuelos.get(key)
        }

        @SuppressLint("NewApi")
        fun findVuelo(modalidad: String, origen:String, destino:String, salida:String, llegada:String): ArrayList<Vuelo> {
            var vuelosTem = ArrayList<Vuelo>()
            val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH)
            val dateSalida = LocalDate.parse(salida, formatter)
            val dateLlegada = LocalDate.parse(llegada, formatter)
            for (vuelo in vuelos){
                val vueloF = LocalDate.parse(vuelo.fecha, formatter)
                if(modalidad.equals(vuelo.modalidad) && origen.equals(vuelo.rutaId.ciudadOrigen.nombre)
                    && destino.equals(vuelo.rutaId.ciudadDestino.nombre) && vueloF.isAfter(dateSalida)
                    && vueloF.isBefore(dateLlegada)){
                    vuelosTem.add(vuelo)
                }
            }
            return vuelosTem
        }

        fun getVuelos(): ArrayList<Vuelo> {
            return vuelos
        }
    }

    init {

    }

    fun getInstance(): SingletonVuelos {
        return SingletonVuelos
    }
}

