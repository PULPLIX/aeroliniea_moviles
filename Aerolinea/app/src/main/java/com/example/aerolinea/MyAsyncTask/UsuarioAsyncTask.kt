package com.example.aerolinea.MyAsyncTask

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.aerolinea.Coroutines.CoroutinesAsyncTask
import com.example.aerolinea.Model.Usuario
import com.example.aerolinea.View.MainUserActivity
import com.example.aerolinea.databinding.ActivityLoginBinding
import com.example.aerolinea.login_register.Login
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.*
import java.net.URL
import java.util.concurrent.TimeUnit


class UsuarioAsyncTask(private var activity: Login, binding: ActivityLoginBinding) : CoroutinesAsyncTask<Int, Int, String>("MysAsyncTask") {
    private var apiUrl: String = "http://10.0.2.2:8081/Backend/api/usuario/"
    var binding = binding
    var action: String = ""
    var userName: String = ""
    var password: String = ""
    private lateinit var user: Usuario

    override fun doInBackground(vararg params: Int?): String {
        var result = ""

        result = processRequest()

        Log.d("Result", result)
        return result
    }

    fun setApiUrl(action: String){
        apiUrl = "http://10.0.2.2:8081/Backend/api/usuario/"
        this.action = action
        apiUrl += action
    }

    fun setUserAndPassword(id_user: String, password: String){
        userName = id_user
        this.password = password
    }

    private val CONNECT_TIMEOUT = 15L
    private val READ_TIMEOUT = 15L
    private val WRITE_TIMEOUT = 15L

    fun processRequest(): String {
        var result = ""
        var jsonString = "{\"id\": \"$userName\", \"contrasena\": \"$password\"}"

        try {
            val client = OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .build()

            val body = jsonString.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

            val request = Request.Builder()
                    .url(URL(apiUrl))
                    .header("Authorization", "true")
                    .post(body)
                    .build()

            val response = client.newCall(request).execute().use {
                response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                var gson = Gson()

                var usuario = response.body!!.string()

                result = usuario
                Log.d("RESPUESTA", result)
            }

        }
        catch (e: IOException) {
            e.printStackTrace()
            null
        }
        return result
    }

    @Throws(JsonProcessingException::class)
    fun objectToJson(obj: Any): String {
        return ObjectMapper().writeValueAsString(obj)
    }

    @Throws(IOException::class)
    fun jsonToAgentObject(json: String?): Usuario? {
        return if (json == null) { null } else {
            ObjectMapper().readValue<Usuario>(json, Usuario::class.java)
        }
    }

    override fun onPostExecute(result: String?){
        if (progresDialog.isShowing){
            progresDialog.dismiss()
        }
        if (action == "login"){
            login(result.toString())
        }
    }

    fun login(usuario: String){

        val gson = Gson()
        val sType = object : TypeToken<Usuario>() { }.type
        //user = gson.fromJson(usuario, sType)

        if(usuario != null){
            val sp = activity.getSharedPreferences("key", Context.MODE_PRIVATE)
            val ed = sp.edit()
            ed.putString("usuario", usuario)
            ed.apply()
            val intentLoging = Intent(binding.root.context, MainUserActivity::class.java)
            binding.root.context.startActivity(intentLoging)
        }else{
            Toast.makeText(binding.root.context, "Registrese", Toast.LENGTH_SHORT).show()
        }

    }

    val progresDialog = ProgressDialog(binding.root.context)
    override fun onPreExecute() {
        progresDialog.setMessage("Logueandose ...")
        progresDialog.setCancelable(false)
        progresDialog.show()
    }

    override fun onProgressUpdate(vararg values: Int?) {

    }
}