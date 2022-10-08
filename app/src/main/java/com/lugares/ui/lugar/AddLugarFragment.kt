package com.lugares.ui.lugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lugares.R
import com.lugares.databinding.FragmentAddLugarBinding
import com.lugares.model.Lugar
import com.lugares.viewModel.LugarViewModel

class AddLugarFragment : Fragment() {

    //objeto para interactuar con la tabla...
    private lateinit var lugarViewModel: LugarViewModel
    private var _binding: FragmentAddLugarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lugarViewModel =
            ViewModelProvider(this).get(LugarViewModel::class.java)

        _binding = FragmentAddLugarBinding.inflate(inflater, container, false)

        binding.btAdd.setOnClickListener{addLugar() }


        return binding.root
    }

    private fun addLugar() {
        val nombre = binding.etNombre.text.toString() //obtiene el texto del nombre
        if (nombre.isNotEmpty()) {//si se escribio algo en el texto guardalo

            val correo = binding.etCorreo.text.toString() //obtiene el texto del nombre
            val telefono = binding.etTelefono.text.toString() //obtiene el texto del nombre
            val web = binding.etWeb.text.toString() //obtiene el texto del nombre
            val lugar = Lugar(0, nombre, correo, telefono, web,
                0.0, 0.0, 0.0, "", "")

            //se procede a registrar el nuevo lugar...
            lugarViewModel.saveLugar(lugar)
            Toast.makeText(requireContext(),
                getString(R.string.msg_lugar_added),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addLugarFragment_to_nav_lugar)
        } else { //no se puede registrar el lugar... falta info
            Toast.makeText(requireContext(),
                getString(R.string.msg_data),
                Toast.LENGTH_LONG ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}