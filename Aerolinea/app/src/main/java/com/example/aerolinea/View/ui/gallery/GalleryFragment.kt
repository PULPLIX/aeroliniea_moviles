package com.example.aerolinea.View.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.aerolinea.R
import com.example.aerolinea.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        var view = inflater.inflate(R.layout.fragment_gallery, container, false)

//        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
//        val root: View = binding.root

//        val textView: com.google.android.material.textfield.TextInputLayout = binding.tfOrigen
//        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}