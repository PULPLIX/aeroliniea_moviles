package com.example.aerolinea.login_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.aerolinea.Model.model
import com.example.aerolinea.View.UserView
import com.example.aerolinea.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun loginUser(view: View) {
        val usuario:String = user.text.toString()
        val password:String = password.text.toString()

        val model = model()
        if (model.getInstance().findUser(usuario) != null) {
            if (model.getInstance().confirmUser(usuario,password)){
                val intentLoging = Intent(this, UserView::class.java)
                startActivity(intentLoging)
            }else{
                Toast.makeText(applicationContext, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(applicationContext, "Registrese", Toast.LENGTH_SHORT).show()
        }
    }
}