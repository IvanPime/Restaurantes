package com.diplomado.laboratorio2.ui.restaurantes

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.diplomado.laboratorio2.R
import com.diplomado.laboratorio2.adapter.ImagenesRestauranteAdapter
import com.diplomado.laboratorio2.databinding.FragmentDetalleRestauranteBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetalleRestauranteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetalleRestauranteFragment : Fragment() {
    private val args: DetalleRestauranteFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetalleRestauranteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalleRestauranteBinding.inflate(inflater)
        var root = binding.root
        binding.tvNombreRestaurante.text = "Nombre: " + args.restaurante.nombre
        binding.tvCalificacionRestaurante.text = "Calificacion: " + args.restaurante.calificacion.toString()
        binding.tvAnioRestaurante.text = "Año: " + args.restaurante.anio.toString()
        binding.tvCostoPromedioRestaurante.text = "Costo Promedio: " + args.restaurante.costoPromedio.toString()
        binding.tvResenia.text = "Reseña: " + args.restaurante.resenias[0].resenia
        binding.loadingWheel.visibility = View.VISIBLE
        Glide.with(root.context).load(args.restaurante.imagenes[0]).listener(object:
            RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                binding.loadingWheel.visibility = View.GONE
                binding.imgRestaurante.setImageResource(R.drawable.ic_image_not_supported_black)
                return false
            }
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                binding.loadingWheel.visibility = View.GONE
                return false
            }

        }).error(R.drawable.ic_image_not_supported_black).into(binding.imgRestaurante)

        Log.i("DEBUG_INFO", "${args.restaurante.imagenes}")
        val adaptador = ImagenesRestauranteAdapter(args.restaurante.imagenes)
        binding.rvImagenesRestaurante.setHasFixedSize(true)
        binding.rvImagenesRestaurante.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvImagenesRestaurante.addItemDecoration(
            DividerItemDecoration(root.context, DividerItemDecoration.VERTICAL)
        )
        binding.rvImagenesRestaurante.adapter = adaptador

        return root
    }

}