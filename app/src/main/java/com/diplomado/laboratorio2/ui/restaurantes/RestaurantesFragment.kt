package com.diplomado.laboratorio2.ui.restaurantes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diplomado.laboratorio2.APIService
import com.diplomado.laboratorio2.adapter.RestauranteAdapter
import com.diplomado.laboratorio2.databinding.FragmentRestaurantesBinding
import com.diplomado.laboratorio2.model.Restaurante
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantesFragment : Fragment() {

    private var _binding: FragmentRestaurantesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: RestauranteAdapter
    private val restaurantes = mutableListOf<Restaurante>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecViewRestaurantes()

        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getRestaurantes("listaRestaurantes")
            val response = call.body()
            activity?.runOnUiThread {
                if (call.isSuccessful) {
                    Log.i("DEBUG_INFO", "${response}")
                    var restaurantesAux = response?.restaurantes ?: emptyList()
                    restaurantes.clear()
                    restaurantes.addAll(restaurantesAux)
                    adapter.notifyDataSetChanged()
                } else {

                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://demo0551910.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initRecViewRestaurantes() {
        adapter = RestauranteAdapter(restaurantes) {
            Log.i("DEBUG_INFO", "${it.idRestaurante}")
            findNavController().navigate(RestaurantesFragmentDirections.actionNavRestaurantesToDetalleRestauranteFragment(it))
        }
        binding.rvRestaurantes.setHasFixedSize(true)
        binding.rvRestaurantes.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvRestaurantes.adapter = adapter
    }
}