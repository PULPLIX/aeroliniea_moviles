package com.example.aerolinea.View.ui.perfil

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.aerolinea.R
import com.example.aerolinea.databinding.FragmentHomeBinding
import com.example.aerolinea.databinding.FragmentPerfilBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PerfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerfilFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentPerfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnEditar.setOnClickListener{ habilitarEdicion() }
        return root
    }

    fun habilitarEdicion() {
        Log.d("HABILITANDO EDICION","SI ENTRA")
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

            binding.btnEditar.text = "Cancelar"
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

            binding.btnEditar.text = "Editar"
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PerfilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PerfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}