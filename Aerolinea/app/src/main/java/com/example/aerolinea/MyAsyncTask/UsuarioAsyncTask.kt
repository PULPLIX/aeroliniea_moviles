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
    private val CONNECT_TIMEOUT = 15L
    private val READ_TIMEOUT = 15L
    private val WRITE_TIMEOUT = 15L
    private var user: Usuario? = null

    override fun doInBackground(vararg params: Int?): String {
        var result = ""
        result = processRequest()
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

    fun setUser(id: String,passwordUpdate: String,nombre: String,apellidos: String,correo: String,
                fechaNacimiento: String, direccion : String, telefonoTrabajo: String,celular: String,
                rol: String){
        user = Usuario(id,passwordUpdate,nombre,apellidos,correo,fechaNacimiento,direccion, telefonoTrabajo,celular, rol)
    }

    fun insertRequest(): String {
        var result = ""
        var jsonString = Gson().toJson(user)

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

            client.newCall(request).execute().use {
                    response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                var usuario = response.body!!.string()
                result = usuario
                Log.d("RESULTADO", result)
            }

        }
        catch (e: IOException) {
            e.printStackTrace()
            null
        }
        return result
    }


    fun updateRequest(): String {
        var result = ""
        var jsonString = Gson().toJson(user)

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
                .put(body)
                .build()

            client.newCall(request).execute().use {
                    response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                var usuario = response.body!!.string()
                result = usuario
            }

        }
        catch (e: IOException) {
            e.printStackTrace()
            null
        }
        return result
    }

    fun loginRequest(): String{
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

            client.newCall(request).execute().use {
                    response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                var usuario = response.body!!.string()

                result = usuario
            }

        }
        catch (e: IOException) {
            e.printStackTrace()
            null
        }
        return result
    }

    fun processRequest(): String {
        var result = ""

        if(action=="login")
            result = loginRequest()
        if(action=="actualizar")
            result = updateRequest()
        if(action=="insertar")
            result = insertRequest()

        return result
    }

    override fun onPostExecute(result: String?){
        if (progresDialog.isShowing){
            progresDialog.dismiss()
        }
        if (action == "login"){
            login(result.toString())
        }
        if (action == "actualizar"){
            actualizar(result.toString())
        }
        if (action == "insertar"){
            insertar(result.toString())
        }
    }

    fun insertar(usuario: String){
        Log.d("INSERTADO", usuario)
    }

    fun actualizar(usuario: String){
    }

    fun login(usuario: String){

        if(usuario.isNotEmpty()){
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
        if(action == "login") {
            progresDialog.setMessage("Logueandose ...")
            progresDialog.setCancelable(false)
            progresDialog.show()
        }
        if(action == "actualizar") {
            progresDialog.setMessage("actualizando ...")
            progresDialog.setCancelable(false)
            progresDialog.show()
        }
        if(action == "insertar") {
            progresDialog.setMessage("Registrandose ...")
            progresDialog.setCancelable(false)
            progresDialog.show()
        }
    }

    override fun onProgressUpdate(vararg values: Int?) {

    }
}