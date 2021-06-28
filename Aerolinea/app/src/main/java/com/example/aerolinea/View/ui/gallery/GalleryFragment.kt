package com.example.aerolinea.View.ui.gallery

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aerolinea.Model.ModelTiquetes
import com.example.aerolinea.Model.Tiquete
import com.example.aerolinea.Model.Usuario
import com.example.aerolinea.MyAsyncTask.TiquetesAsycTask
import com.example.aerolinea.View.ui.tiquete.TiqueteActivity
import com.example.aerolinea.adapters.SwipeGesture
import com.example.aerolinea.adapters.TiquetesAdapter
import com.example.aerolinea.databinding.FragmentGalleryBinding
import com.example.aerolinea.util.Constans
import com.google.gson.Gson
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.HashMap
import kotlin.collections.ArrayList


class GalleryFragment : Fragment() {
    var tiquetes: ArrayList<Tiquete> = ArrayList()
    private lateinit var adapter: TiquetesAdapter

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var taskTiquetes: TiquetesAsycTask? = null
    private lateinit var userSession: Usuario

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        tiquetes.clear()
        tiquetes = ModelTiquetes().getInstance().getTiquetesUsuario(getUser().nombre)
        adapter = TiquetesAdapter(tiquetes)
        var tiquetesTem = ArrayList<Tiquete>(tiquetes)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //initRecycler();
        userSession = getUser()
        // Search view
        //searchView()
        startService()

        return root
    }

    fun getUser(): Usuario{
        val sp = context?.getSharedPreferences("key", Context.MODE_PRIVATE)
        val usuarioSes = sp?.getString("usuario",null)
        var gson = Gson()
        var user = gson.fromJson<Usuario>(usuarioSes, Usuario::class.java)
        return user
    }

    fun startService() {
        if (taskTiquetes?.status == Constans.Companion.Status.RUNNING) {
            taskTiquetes?.cancel(true)
        }
        val map: HashMap<String, String> = hashMapOf(
            "id" to userSession.id,
        )
        // Lista ciudades origen y destino
        taskTiquetes = TiquetesAsycTask(this, binding)
        taskTiquetes!!.setApiUrl("tiquetesUsuario","GET", map)
        taskTiquetes?.execute(10)

    }

    fun searchView(){
        val search = binding.buscador
        binding.rvTiquetes.adapter = adapter

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search.clearFocus()
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText)
                return false
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getTiquetes(): MutableList<Tiquete> {
        return tiquetes
    }



}