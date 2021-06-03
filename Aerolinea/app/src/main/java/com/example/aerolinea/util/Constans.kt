package com.example.aerolinea.util

class Constans {
    companion object{
        const val BASE_URL = "https://192.168.1.107/Backend/api/"
        enum class Status {
            PENDING,
            RUNNING,
            FINISHED
        }
    }
}