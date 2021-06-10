package com.example.aerolinea.View.ui.gallery

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aerolinea.Model.ModelTiquetes
import com.example.aerolinea.Model.ModelVuelos
import com.example.aerolinea.Model.Tiquete
import com.example.aerolinea.adapters.SwipeGesture
import com.example.aerolinea.adapters.TiquetesAdapter
import com.example.aerolinea.adapters.VuelosResultAdapter
import com.example.aerolinea.databinding.FragmentGalleryBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class GalleryFragment : Fragment() {
    var tiquetes: ArrayList<Tiquete> = ArrayList()
    val adapter = TiquetesAdapter(tiquetes)

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
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        tiquetes.clear()
        tiquetes = ModelTiquetes().getInstance().getTiquetes()
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecycler();

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    val swipeGesture = object : SwipeGesture() {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    adapter.deleteItem(viewHolder.bindingAdapterPosition)
                }
                ItemTouchHelper.RIGHT -> {
                    val archiveItem = tiquetes[viewHolder.bindingAdapterPosition]
                    adapter.deleteItem(viewHolder.bindingAdapterPosition)
                    adapter.addItem(tiquetes.size, archiveItem)
                }
            }

        }
        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            RecyclerViewSwipeDecorator.Builder(
                c,
                recyclerView,
                viewHolder,
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )   .addSwipeLeftBackgroundColor(ContextCompat.getColor(context!!, com.example.aerolinea.R.color.rojito))
                .addSwipeLeftActionIcon(com.example.aerolinea.R.drawable.ic_delete_white)
                .addSwipeRightBackgroundColor(ContextCompat.getColor(context!!, com.example.aerolinea.R.color.celestito))
                .addSwipeRightActionIcon(com.example.aerolinea.R.drawable.ic_info_white)
                .create()
                .decorate()
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }


    fun initRecycler() {

        val adapter = TiquetesAdapter(tiquetes)
        binding.rvTiquetes.layoutManager = LinearLayoutManager(context)
        binding?.rvTiquetes?.adapter = adapter

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvTiquetes)
    }

    fun getTiquetes(): MutableList<Tiquete> {
        return tiquetes
    }


}