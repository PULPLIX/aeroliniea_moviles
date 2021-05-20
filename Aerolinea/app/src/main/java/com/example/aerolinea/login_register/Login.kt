package com.example.aerolinea.login_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.aerolinea.Model.model
import com.example.aerolinea.View.MainUserActivity
import com.example.aerolinea.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun loginUser(view: View) {
        val usuario:String = binding.etUser.text.toString()
        val password:String = binding.etPassword.text.toString()

        val model = model()
        if (model.getInstance().findUser(usuario) != null) {
            if (model.getInstance().confirmUser(usuario,password)){
                val intentLoging = Intent(this, MainUserActivity::class.java)
                startActivity(intentLoging)
            }else{
                Toast.makeText(applicationContext, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(applicationContext, "Registrese", Toast.LENGTH_SHORT).show()
        }
    }
    fun goRegister(view: View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }
}