package com.diplomado.laboratorio2.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.diplomado.laboratorio2.APIService
import com.diplomado.laboratorio2.R
import com.diplomado.laboratorio2.databinding.FragmentLoginBinding
import com.diplomado.laboratorio2.ui.restaurantes.RestaurantesFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.login.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofitLogin().create(APIService::class.java).userIsValid("validarUsuario")
                val response = call.body()
                activity?.runOnUiThread {
                    if (call.isSuccessful) {
                        if (response != null) {
                            if (response.estatus) {
                                if (binding.username.text.toString().equals("pime@gmail.com") && binding.password.text.toString().equals("12345678")) {
                                    root.findNavController().navigate(R.id.nav_restaurantes)
                                } else if (binding.username.text.toString().equals("pime2@gmail.com") && binding.password.text.toString().equals("12345678")) {
                                    root.findNavController().navigate(R.id.nav_restaurantes)
                                } else if (binding.username.text.toString().equals("pime3@gmail.com") && binding.password.text.toString().equals("12345678")) {
                                    root.findNavController().navigate(R.id.nav_restaurantes)
                                } else {
                                    Toast.makeText(root.context, "Usuario incorrecto", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    } else {

                    }
                }
            }

        }

        return root
    }

    private fun getRetrofitLogin(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(" http://demo4851577.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}