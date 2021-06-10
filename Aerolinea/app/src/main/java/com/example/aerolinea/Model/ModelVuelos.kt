package com.example.aerolinea.Model

import android.widget.Toast
import java.lang.Exception


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
            val ny = Ciudad(1, "NY")
            val aj = Ciudad(2, "Alajuela")
            val horario = Horario(2, "Lunes", 3)
            val ruta = Ruta(1, 200.0, 20.0, ny, aj, horario)

            addVuelo(
                Vuelo(
                    1,
                    "ida y vuelta",
                    "2",
                    "Mar 20, 2021",
                    avion,
                    ruta,
                    listOf<Tiquete>()
                )
            )
            addVuelo(
                Vuelo(
                    1,
                    "ida y vuelta",
                    "2",
                    "Mar 20, 2021",
                    avion,
                    ruta,
                    listOf<Tiquete>()
                )
            )
            addVuelo(
                Vuelo(
                    1,
                    "ida y vuelta",
                    "2",
                    "Mar 20, 2021",
                    avion,
                    ruta,
                    listOf<Tiquete>()
                )
            )
            addVuelo(
                Vuelo(
                    1,
                    "ida y vuelta",
                    "2",
                    "Mar 20, 2021",
                    avion,
                    ruta,
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

