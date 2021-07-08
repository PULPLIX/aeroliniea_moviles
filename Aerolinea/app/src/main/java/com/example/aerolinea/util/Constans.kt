package com.example.aerolinea.util

class Constans {
    companion object{
        const val BASE_URL = "http://192.168.5.108:8081/Backend/api"
        const val BASE_SOCKET = "ws://192.168.5.108:8081/Backend"
        enum class Status {
            PENDING,
            RUNNING,
            FINISHED
        }
    }
}