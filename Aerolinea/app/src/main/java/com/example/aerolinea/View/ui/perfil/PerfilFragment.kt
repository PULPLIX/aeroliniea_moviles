package com.example.aerolinea.View.ui.perfil

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aerolinea.Model.Usuario
import com.example.aerolinea.databinding.FragmentPerfilBinding
import com.google.gson.Gson
import android.content.SharedPreferences
import android.widget.Toast
import com.example.aerolinea.Model.model
import com.example.aerolinea.MyAsyncTask.UsuarioAsyncTask
import com.example.aerolinea.databinding.ActivityLoginBinding
import com.example.aerolinea.login_register.Login
import com.example.aerolinea.util.Constans

class PerfilFragment : Fragment(){
    private var _binding: FragmentPerfilBinding? = null

    private lateinit var bindingL: ActivityLoginBinding

    private val binding get() = _binding!!
    private lateinit var usuario:Usuario
    var taskUsuario: UsuarioAsyncTask? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root
        usuario = getUser()
        loadView()
        binding.btnEditar.setOnClickListener{ habilitarEdicion() }

        bindingL = ActivityLoginBinding.inflate(layoutInflater)


        return root
    }

    fun getUser(): Usuario{
        val sp = context?.getSharedPreferences("key", Context.MODE_PRIVATE)
        val usuarioSession = sp?.getString("usuario",null)
        var gson = Gson()
        var user = gson.fromJson(usuarioSession, Usuario::class.java)
        return user
    }

    fun loadView(){
        //Text Views
        binding.tvCorreo.text = usuario.correo
        binding.tvNombre.text = usuario.nombre
        binding.tvDireccion.text = usuario.direccion
        binding.tvCTelephone.text = usuario.telefono_Trabajo
        binding.tvCellphone.text = usuario.celular

        //Edit Text
        binding.etCorreo.setText(usuario.correo)
        binding.etDireccion.setText(usuario.direccion)
        binding.etTelefono.setText(usuario.telefono_Trabajo)
        binding.etCelular.setText(usuario.celular)
    }

    fun updateUser(){
        usuario.correo = binding.etCorreo.text.toString()
        usuario.direccion = binding.etDireccion.text.toString()
        usuario.telefono_Trabajo = binding.etTelefono.text.toString()
        usuario.celular = binding.etCelular.text.toString()
        usuario.contrasena = binding.etPasswordPerfil.text.toString()

        /*
        if (taskUsuario?.status == Constans.Companion.Status.RUNNING) {
            taskUsuario?.cancel(true)
        }
         */

        // Lista ciudades origen y destino
        taskUsuario = UsuarioAsyncTask(Login(), bindingL)
        taskUsuario!!.setApiUrl("actualizar")
        Log.d("USUARIO", usuario.toString())
        taskUsuario!!.setUser(usuario.id,usuario.contrasena,usuario.nombre,usuario.apellidos,
            usuario.correo,usuario.fecha_Nacimiento,usuario.direccion,usuario.telefono_Trabajo,
            usuario.celular,usuario.rol)

        taskUsuario?.execute(10)

        val sp = context?.getSharedPreferences("key", Context.MODE_PRIVATE)
        val ed = sp?.edit()
        val gson = Gson()
        val usuarioJSON =  gson.toJson(usuario)
        ed?.putString("usuario", usuarioJSON)
        ed?.commit()

        model.editUser(usuario)
        Toast.makeText(context, "Actualizado correctamente", Toast.LENGTH_SHORT).show()
    }

    fun habilitarEdicion() {
        if (binding.etCelular.visibility == View.GONE) {
            binding.etCorreo.visibility = View.VISIBLE
            binding.etDireccion.visibility = View.VISIBLE
            binding.etCelular.visibility = View.VISIBLE
            binding.etTelefono.visibility = View.VISIBLE
            binding.etPasswordPerfil.visibility = View.VISIBLE

            //Text Views
            binding.tvDireccion.visibility = View.GONE
            binding.tvCorreo.visibility = View.GONE
            binding.tvCTelephone.visibility = View.GONE
            binding.tvCellphone.visibility = View.GONE

            binding.btnEditar.text = "Guardar"
        } else {
            //Input Text
            binding.etCorreo.visibility = View.GONE
            binding.etCelular.visibility = View.GONE
            binding.etDireccion.visibility = View.GONE
            binding.etTelefono.visibility = View.GONE
            binding.etPasswordPerfil.visibility = View.GONE
            //Text Views
            binding.tvDireccion.visibility = View.VISIBLE
            binding.tvCorreo.visibility = View.VISIBLE
            binding.tvCTelephone.visibility = View.VISIBLE
            binding.tvCellphone.visibility = View.VISIBLE
            updateUser()
            loadView()
            binding.btnEditar.text = "Editar"
        }
    }

}