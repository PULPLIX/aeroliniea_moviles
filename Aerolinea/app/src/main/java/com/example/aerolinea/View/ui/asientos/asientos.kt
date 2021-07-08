package com.example.aerolinea.View.ui.asientos

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.aerolinea.R
import com.example.aerolinea.databinding.ActivityAsientosVueloBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [asientos.newInstance] factory method to
 * create an instance of this fragment.
 */
class asientos : Fragment() {

    private var _binding: ActivityAsientosVueloBinding? = null

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
        _binding = ActivityAsientosVueloBinding.inflate(inflater, container, false)
        val root: View = binding.root
        cargarAsientos()
        return root
    }

    private fun cargarAsientos(){
        var asientos: Array<IntArray> = Array(3) { IntArray(6) }

        val myArray = arrayOf(arrayOf("A", "B","C"),arrayOf("1", "2","2"))

        for (innerArray in myArray) {
            val layout: LinearLayout = LinearLayout(context)
            layout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            layout.orientation = LinearLayout.HORIZONTAL

            for(element in innerArray) {
                val btn: Button = Button(context)
                btn.layoutParams = LinearLayout.LayoutParams(120, 120)
                btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#15CE3E"))
                layout.addView(btn)
            }
            binding.linearAsientos.addView(layout)
        }
    }
}