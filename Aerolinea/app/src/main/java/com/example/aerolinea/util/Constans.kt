package com.example.aerolinea.util

class Constans {
    companion object{
        const val BASE_URL = "https://10.0.2.2/Backend/api/"
        enum class Status {
            PENDING,
            RUNNING,
            FINISHED
        }
    }
}