package com.example.aerolinea.login_register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aerolinea.Model.model
import com.example.aerolinea.MyAsyncTask.TiquetesAsyncTask
import com.example.aerolinea.MyAsyncTask.UsuarioAsyncTask
import com.example.aerolinea.View.MainUserActivity
import com.example.aerolinea.databinding.ActivityLoginBinding
import com.example.aerolinea.util.Constans
import java.util.HashMap


class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var taskUsuario: UsuarioAsyncTask? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun loginUser(view: View) {
        val usuario: String = binding.etUser.text.toString()
        val password: String = binding.etPassword.text.toString()

        if (taskUsuario?.status == Constans.Companion.Status.RUNNING) {
            taskUsuario?.cancel(true)
        }

        // Lista ciudades origen y destino
        taskUsuario = UsuarioAsyncTask(this, binding)
        taskUsuario!!.setApiUrl("login")
        taskUsuario!!.setUserAndPassword(usuario,password)
        taskUsuario?.execute(10)

    }

    /*
    fun loginUser(view: View) {
        val usuario: String = binding.etUser.text.toString()
        val password: String = binding.etPassword.text.toString()

        val model = model()
        val usuarioSession = model.getInstance().findUser(usuario)
        if (usuarioSession != null) {
            if (model.getInstance().confirmUser(usuario, password)) {
                val sp = getSharedPreferences("key", Context.MODE_PRIVATE)
                val ed = sp.edit()
                val gson = Gson()
                val usuarioJSON =  gson.toJson(usuarioSession)
                ed.putString("usuario", usuarioJSON)
                ed.apply()
                val intentLoging = Intent(this, MainUserActivity::class.java)
                startActivity(intentLoging)
            } else {
                Toast.makeText(applicationContext, "Contraseña incorrecta", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(applicationContext, "Registrese", Toast.LENGTH_SHORT).show()
        }
    }

     */

    fun goRegister(view: View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }
}