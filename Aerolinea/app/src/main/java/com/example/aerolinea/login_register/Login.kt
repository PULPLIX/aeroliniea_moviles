package com.example.aerolinea.login_register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aerolinea.Model.Usuario
import com.example.aerolinea.Model.model
import com.example.aerolinea.View.MainUserActivity
import com.example.aerolinea.databinding.ActivityLoginBinding
import com.google.gson.Gson


class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

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
                ed.commit()
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

    fun goRegister(view: View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }
}