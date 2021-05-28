package com.example.aerolinea.View.ui.tiquete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aerolinea.R
import com.example.aerolinea.databinding.FragmentPerfilBinding
import com.example.aerolinea.databinding.FragmentTiqueteBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [tiquete.newInstance] factory method to
 * create an instance of this fragment.
 */
class tiquete : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentTiqueteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTiqueteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


}