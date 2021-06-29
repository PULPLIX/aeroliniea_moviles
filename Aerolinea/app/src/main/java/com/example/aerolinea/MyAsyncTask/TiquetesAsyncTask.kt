package com.example.aerolinea.MyAsyncTask

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.util.Log
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aerolinea.Coroutines.CoroutinesAsyncTask
import com.example.aerolinea.Model.Tiquete
import com.example.aerolinea.Model.Usuario
import com.example.aerolinea.View.ui.gallery.GalleryFragment
import com.example.aerolinea.View.ui.tiquete.TiqueteActivity
import com.example.aerolinea.adapters.SwipeGesture
import com.example.aerolinea.adapters.TiquetesAdapter
import com.example.aerolinea.databinding.FragmentGalleryBinding
import com.example.aerolinea.databinding.FragmentHomeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class TiquetesAsyncTask(private var activity: GalleryFragment?, binding: FragmentGalleryBinding) :
    CoroutinesAsyncTask<Int, Int, String>("TiquetesAsyncTask") {
    private var apiUrl: String = "http://10.0.2.2:8081/Backend/api/usuario"
    var binding = binding
    var action: String = ""
    val progresDialog = ProgressDialog(activity?.context)
    var method: String = ""
    val usario = getUser()
    private lateinit var adapter: TiquetesAdapter

    lateinit var tiquetes: ArrayList<Tiquete>

    override fun doInBackground(vararg params: Int?): String {
        var result = ""
        result = processRequest()
        return result
    }

    fun setApiUrl(action: String, method: String, parameters: HashMap<String,String>?){
        apiUrl = "http://10.0.2.2:8081/Backend/api/usuario/"
        this.action = action
        this.method = method
        apiUrl += action
        addParamsToUrl(parameters)

    }

    fun addParamsToUrl(parameters: HashMap<String,String>?){
        if (parameters != null) {
            apiUrl += "/"
            for ((key, value) in parameters) {
                apiUrl +="$value"
            }
        }
    }

    fun getUser(): Usuario {
        val sp = activity?.context?.getSharedPreferences("key", Context.MODE_PRIVATE)
        val usuarioSession = sp?.getString("usuario",null)
        var gson = Gson()
        var user = gson.fromJson<Usuario>(usuarioSession, Usuario::class.java)
        return user
    }

    fun processRequest(): String {
        var result = ""
        try {
            val url: URL
            var urlConnection: HttpURLConnection? = null
            try {
                url = URL(apiUrl)
                urlConnection = url
                    .openConnection() as HttpURLConnection
                val `in` = urlConnection.inputStream
                val isw = InputStreamReader(`in`)
                var data = isw.read()
                while (data != -1) {
                    result += data.toChar()
                    data = isw.read()
                }

                // return the data to onPostExecute method
                return result
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                urlConnection?.disconnect()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "Exception: " + e.message
        }
        return result
    }

    override fun onPostExecute(result: String?) {
        if (progresDialog.isShowing) progresDialog.dismiss()

        if (action == "tiquetesUsuario") {
            listarTiquetes(result.toString())
        }
        if (action == "buscar") {
//            listarVuelos(result.toString())
            print(result.toString())
        }
    }

    fun listarTiquetes(tiquetesResult: String){

        val sType = object : TypeToken<ArrayList<Tiquete>>() {}.type

        if(tiquetesResult != null){
            tiquetes = Gson().fromJson(tiquetesResult, sType)
        }

        if(tiquetes == null){
            tiquetes = ArrayList()
        }
        initRecycler()
        searchView()

    }

    override fun onPreExecute() {
        progresDialog.setMessage("Cargando tiquetes ...")
        progresDialog.setCancelable(false)
        progresDialog.show()
    }

    override fun onProgressUpdate(vararg values: Int?) {
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

    val swipeGesture = object : SwipeGesture() {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    adapter.deleteItem(viewHolder.bindingAdapterPosition)
                    initRecycler()
                }
                ItemTouchHelper.RIGHT -> {
                    val intent = Intent(binding.root.context, TiqueteActivity::class.java)
                    var Tiquete = tiquetes[viewHolder.bindingAdapterPosition]
                    intent.putExtra("Tiquete", Tiquete)
                    initRecycler()
                    binding.root.context.startActivity(intent)
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
            ).addSwipeLeftBackgroundColor(
                    ContextCompat.getColor(
                            binding.root.context,
                            com.example.aerolinea.R.color.rojito
                    )
            )
                    .addSwipeLeftActionIcon(com.example.aerolinea.R.drawable.ic_delete_white)
                    .addSwipeRightBackgroundColor(
                            ContextCompat.getColor(
                                    binding.root.context,
                                    com.example.aerolinea.R.color.celestito
                            )
                    )
                    .addSwipeRightActionIcon(com.example.aerolinea.R.drawable.ic_info_white)
                    .create()
                    .decorate()
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

    fun initRecycler() {
        adapter = TiquetesAdapter(tiquetes)
        binding.rvTiquetes.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvTiquetes.adapter = adapter

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvTiquetes)
    }



}