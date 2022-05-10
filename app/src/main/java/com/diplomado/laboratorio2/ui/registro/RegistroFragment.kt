package com.example.laboratorio2.ui.registro

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.diplomado.laboratorio2.databinding.FragmentRegistroBinding
import java.util.regex.Pattern


class RegistroFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentRegistroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnRegistrar.setOnClickListener(this)
        binding.editTextNombre.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    if (s.toString().trim { it <= ' ' }.isEmpty()) {
                        binding.textInputLayoutNombre.setErrorEnabled(true)
                        binding.textInputLayoutNombre.setError("Escribe el nombre")
                    } else {
                        binding.textInputLayoutNombre.setErrorEnabled(false)
                        binding.textInputLayoutNombre.setError("")
                    }
                }
            })

        binding.editTextEmail.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    if (s.toString().trim { it <= ' ' }.isEmpty()) {
                        binding.textInputLayoutEmail.setErrorEnabled(true)
                        binding.textInputLayoutEmail.setError("Escribe el email")
                    } else {
                        binding.textInputLayoutEmail.setErrorEnabled(false)
                        binding.textInputLayoutEmail.setError("")
                    }
                }
            })

        binding.editTextPassword.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    if (s.toString().trim { it <= ' ' }.isEmpty()) {
                        binding.textInputLayoutPassword.setErrorEnabled(true)
                        binding.textInputLayoutPassword.setError("Escribe el password")
                    } else {
                        binding.textInputLayoutPassword.setErrorEnabled(false)
                        binding.textInputLayoutPassword.setError("")
                    }
                }
            })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        val password = binding.editTextPassword.text.toString();
        if (validarCamposForm() && validatePassword()) {
        }
    }

    private fun validarCamposForm(): Boolean {
        if (binding.editTextNombre.text.toString() == "") {
            binding.textInputLayoutNombre.setError("Escribe el nombre")
            return false
        }
        if (binding.editTextEmail.text.toString() == "") {
            binding.textInputLayoutEmail.setError("Escribe el email")
            return false
        }
        return true
    }

    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +
                "(?=\\S+$)" +  // no white spaces
                "(?=.*[0-9])" + //at least 1 digit
                "(?=.*[a-zA-Z])" + // any letter
                ".{6}" +  // at least 6 characters
                "$"
    )
    private fun validatePassword(): Boolean {
        val passwordInput: String = binding.editTextPassword.text.toString().trim()
        // if password field is empty
        // it will display error message "Field can not be empty"
        return if (passwordInput.isEmpty()) {
            binding.textInputLayoutPassword.setError("Escribe un password")
            false
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            binding.textInputLayoutPassword.setError("Escribe un password de 6 caracteres y que incluya " +
                    "un numero y una letra al menos")
            false
        } else {
            binding.textInputLayoutPassword.setErrorEnabled(false)
            binding.textInputLayoutPassword.setError("")
            true
        }
    }
}