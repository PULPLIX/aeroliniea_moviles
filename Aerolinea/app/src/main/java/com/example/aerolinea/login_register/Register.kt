package com.example.aerolinea.login_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.aerolinea.Model.Usuario
import com.example.aerolinea.Model.model
import com.example.aerolinea.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun register(view:View){
        if( binding.password.text.toString().equals(binding.confirmPassword.text.toString()) ){
            val usuario1 = Usuario("correo",binding.user.text.toString(), binding.password.text.toString(),"1","1","Heredia centro","1")
            val model = model()
            model.getInstance().addUser(usuario1.nombre, usuario1);
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(applicationContext, "Las contraseñas no coninciden", Toast.LENGTH_SHORT).show()
        }
    }

}