package com.example.aerolinea.Model

import java.lang.Exception

class ModelTiquetes {

    companion object SingletonTiquetes {
        private var tiquetes: ArrayList<Tiquete> = ArrayList()
        private var ai: Int = 0

        init {
            // Aqui se inicializan las variables y metodos
        }

        fun addTiquete(tiquete: Tiquete): Boolean {
            try {
                tiquetes.add(tiquete)
                ai++
            } catch (e: Exception) {
                throw e
            } finally {
                return true
            }
        }

        fun findTicket(key: Int): Tiquete? {
            return tiquetes.get(key)
        }

        fun getTiquetesUsuario(usuarioId: String): ArrayList<Tiquete> {
            var tiquetesUsuario: ArrayList<Tiquete> = ArrayList()

            for (tiquete in tiquetes) {
                if (tiquete.usuarioId.nombre == usuarioId) {
                    tiquetesUsuario.add(tiquete)
                }
            }
            return tiquetesUsuario
        }

        fun deleteTiqueteUsuario(id_tiquete: Int): Boolean {

            for (tiquete in tiquetes) {
                if (tiquete.id == id_tiquete) {
                    tiquetes.remove(tiquete)
                    return true
                }
            }
            return false
        }

        fun getTiquetesVuelo(vueloId: Int): ArrayList<Tiquete> {
            var tiquetesVuelo: ArrayList<Tiquete> = ArrayList()
            for (tiquete in tiquetes){
                if(tiquete.vueloId.id == vueloId){
                    tiquetesVuelo.add(tiquete)
                }
            }
            return tiquetesVuelo
        }


        fun getTiquetes(): ArrayList<Tiquete> {
            return tiquetes
        }

        fun getAutoIncrement(): Int {
            return ai
        }
    }

    init {
        // Aqui se inicializan las variables o lo que se necesite en model
    }

    fun getInstance(): SingletonTiquetes {
        return SingletonTiquetes
    }
}

