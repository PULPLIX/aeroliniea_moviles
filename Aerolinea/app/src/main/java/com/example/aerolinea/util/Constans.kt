package com.example.aerolinea.util

class Constans {
    companion object{
        const val BASE_URL = "http://10.0.2.2:8081/Backend/api"
        const val BASE_SOCKET = "ws://10.0.2.2:8081/Backend"
        enum class Status {
            PENDING,
            RUNNING,
            FINISHED
        }
    }
}