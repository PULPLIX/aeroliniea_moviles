package com.example.aerolinea.Socket

import android.util.Log
import com.example.aerolinea.util.Constans.Companion.BASE_SOCKET
import com.google.gson.Gson
import tech.gusavila92.websocketclient.WebSocketClient
import java.net.URI
import java.net.URISyntaxException

class Socket(socketName: String) {

    lateinit var webSocketClient: WebSocketClient
    private var result = ""
    private var socketName = socketName
    init {
        createWebSocketClient()
    }

    fun setResult(result: String){
        this.result = result
    }

    fun createWebSocketClient() {
        val uri: URI
        try {
            // Connect to local host
            uri = URI("$BASE_SOCKET/$socketName")
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