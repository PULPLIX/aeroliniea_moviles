package com.example.aerolinea.Socket

import android.util.Log
import com.google.gson.Gson
import tech.gusavila92.websocketclient.WebSocketClient
import java.net.URI
import java.net.URISyntaxException

class Socket {

    lateinit var webSocketClient: WebSocketClient
    private var result = ""

    fun setResult(result: String){
        this.result = result
    }

    fun createWebSocketClient(socketName: String) {
        val uri: URI
        try {
            // Connect to local host
            uri = URI("ws://10.0.2.2:8081/Backend/$socketName")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            return
        }
        webSocketClient = object : WebSocketClient(uri) {
            override fun onOpen() {
                Log.d("SOCKET", "SE A CREADO")

            }

            override fun onTextReceived(s: String) {

            }

            override fun onBinaryReceived(data: ByteArray) {}
            override fun onPingReceived(data: ByteArray) {}
            override fun onPongReceived(data: ByteArray) {}
            override fun onException(e: Exception) {
                println(e.message)
            }

            override fun onCloseReceived() {
                Log.i("WebSocket", "Closed ")
            }
        }
        webSocketClient.setConnectTimeout(10000)
        webSocketClient.setReadTimeout(60000)
        webSocketClient.enableAutomaticReconnection(5000)
        webSocketClient.connect()
    }
}