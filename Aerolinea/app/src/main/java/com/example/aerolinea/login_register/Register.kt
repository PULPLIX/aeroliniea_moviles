package com.example.aerolinea.login_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.aerolinea.Model.Usuario
import com.example.aerolinea.Model.model
import com.example.aerolinea.databinding.ActivityLoginBinding
import com.example.aerolinea.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun register(view:View){
        if( password.text.toString().equals(confirmPassword.text.toString()) ){
            val usuario1 = Usuario(user.text.toString(), password.text.toString(), "standar")
            val model = model()
            model.getInstance().addUser(usuario1.nombre, usuario1);
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(applicationContext, "Las contraseñas no coninciden", Toast.LENGTH_SHORT).show()
        }
    }

}