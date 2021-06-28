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
            val usuario1 = Usuario("12",binding.correo.text.toString(),binding.nombre.text.toString(),
                    binding.password.text.toString(),binding.celular.text.toString(),
                    binding.telefono.text.toString(),binding.direccion.text.toString(),"1")
            val model = model()
            if(usuario1.correo.isNotEmpty() && usuario1.nombre.isNotEmpty() && usuario1.celular.isNotEmpty()
                    && usuario1.telefono.isNotEmpty() && usuario1.direccion.isNotEmpty() && usuario1.password.isNotEmpty()){
                model.getInstance().addUser(usuario1.correo, usuario1);
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext, "Rellene todos los campos", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(applicationContext, "Las contraseñas no coninciden", Toast.LENGTH_SHORT).show()
        }
    }

    fun login(view: View){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }


}