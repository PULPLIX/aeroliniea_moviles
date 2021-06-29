package com.example.aerolinea.login_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.example.aerolinea.Model.Usuario
import com.example.aerolinea.Model.model
import com.example.aerolinea.MyAsyncTask.UsuarioAsyncTask
import com.example.aerolinea.databinding.ActivityLoginBinding
import com.example.aerolinea.databinding.ActivityRegisterBinding
import com.example.aerolinea.util.DatePickerFragment
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    var taskUsuario: UsuarioAsyncTask? = null
    private lateinit var bindingL: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        bindingL = ActivityLoginBinding.inflate(layoutInflater)

        btnShowDate.setOnClickListener{
            showDatePickerDialog()
        }
    }

    fun showDatePickerDialog(){
        val datePicker = DatePickerFragment{ day, month, year ->  onDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager,"datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year:Int){
        fechaNacimiento.setText("$day/$month/$year")
    }

    fun register(view:View){
        if( binding.password.text.toString().equals(binding.confirmPassword.text.toString()) ){
            val usuario1 = Usuario(binding.cedula.text.toString(),binding.password.text.toString(),binding.nombre.text.toString(),
                binding.apellidos.text.toString(),binding.correo.text.toString(),binding.fechaNacimiento.text.toString(),
                binding.direccion.text.toString(),binding.telefono.text.toString(),binding.celular.text.toString(),"0")
            if(usuario1.correo.isNotEmpty() && usuario1.nombre.isNotEmpty() && usuario1.celular.isNotEmpty()
                    && usuario1.telefono_Trabajo.isNotEmpty() && usuario1.direccion.isNotEmpty() && usuario1.contrasena.isNotEmpty()
                && usuario1.apellidos.isNotEmpty() && usuario1.fecha_Nacimiento.isNotEmpty() && usuario1.correo.isNotEmpty()){

                    Log.d("USER TO REGISTER", usuario1.toString())
                    taskUsuario = UsuarioAsyncTask(Login(), bindingL)
                    taskUsuario!!.setApiUrl("insertar")
                    taskUsuario!!.setUser(usuario1.id,usuario1.contrasena,usuario1.nombre,usuario1.apellidos,
                        usuario1.correo,usuario1.fecha_Nacimiento,usuario1.direccion,usuario1.telefono_Trabajo,
                        usuario1.celular,usuario1.rol)

                    taskUsuario?.execute(10)


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