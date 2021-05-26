package com.example.aerolinea.View.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aerolinea.Model.Vuelo
import com.example.aerolinea.View.ui.DatePickerFragment
import com.example.aerolinea.adapters.VuelosResultAdapter
import com.example.aerolinea.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    val vuelos = listOf<Vuelo>(
        Vuelo("NY US", "Miami US", "Solo ida", "2h", "100", "20", "Mar 20, 2021"),
        Vuelo("Alajuela CR", "Miami US", "Solo ida", "2h", "200", "20", "May 10, 2021"),
        Vuelo("Bogotá Col", "Buenos Aires Arg", "Ida y cvuelta", "2h", "300", "20", "Dec 17, 2021"),
        Vuelo("Santiago Chile", "Lima Perú", "Ida y ida", "2h", "400", "20", "Nov 02, 2021"),
        Vuelo("Quito Perí", "Costa Rica", "Solo ida", "2h", "350", "20", "Jan 28, 2021"),
        Vuelo("Panama City", "Managua Nic", "Ida y vuelta", "2h", "684.2", "20", "Feb 17, 2022")
    )

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.etSalida.setOnClickListener { showDatePickerDialog(binding.etSalida) }
        binding.etRegreso.setOnClickListener { showDatePickerDialog(binding.etRegreso) }
        initRecycler();

        return root
    }

    private fun showDatePickerDialog(et : EditText) {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year,et) }
        datePicker.show(getParentFragmentManager(), "Destino")
    }

    fun onDateSelected(day: Int, month: Int, year: Int,et: EditText) {
        et.setText("$day/$month/$year")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecycler() {
        binding.rvResultado.layoutManager = LinearLayoutManager(context)
        val adapter = VuelosResultAdapter(vuelos)
        binding.rvResultado.adapter = adapter
    }
}