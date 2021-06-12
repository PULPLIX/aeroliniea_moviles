package com.example.aerolinea.View.ui.perfil

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

class PerfilFragment : Fragment() {
    private var _binding: FragmentPerfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var usuario:Usuario
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root
        usuario = getUser()
        loadView()
        binding.btnEditar.setOnClickListener{ habilitarEdicion() }

        return root
    }

    fun getUser(): Usuario{
        val sp = context?.getSharedPreferences("key", Context.MODE_PRIVATE)
        val usuarioSession = sp?.getString("usuario",null)
        var gson = Gson()
        var user = gson.fromJson<Usuario>(usuarioSession, Usuario::class.java)
        return user
    }

    fun loadView(){
        //Text Views
        binding.tvCorreo.text = usuario.correo
        binding.tvNombre.text = usuario.nombre
        binding.tvDireccion.text = usuario.direccion
        binding.tvCTelephone.text = usuario.telefono
        binding.tvCellphone.text = usuario.celular

        //Edit Text
        binding.etCorreo.setText(usuario.correo)
        binding.etDireccion.setText(usuario.direccion)
        binding.etTelefono.setText(usuario.telefono)
        binding.etCelular.setText(usuario.celular)
    }

    fun updateUser(){
        usuario.correo = binding.etCorreo.text.toString()
        usuario.direccion = binding.etDireccion.text.toString()
        usuario.telefono = binding.etTelefono.text.toString()
        usuario.celular = binding.etCelular.text.toString()

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