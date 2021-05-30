package com.example.aerolinea.util

class Constans {
    companion object{
        const val BASE_URL = "https://ca5d8af32562.ngrok.io/Backend/api/"

        enum class Status {
            PENDING,
            RUNNING,
            FINISHED
        }
    }
}