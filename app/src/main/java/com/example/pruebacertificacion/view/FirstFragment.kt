package com.example.pruebacertificacion.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebacertificacion.view.adapter.ConciertoAdapter
import com.example.pruebacertificacion.R
import com.example.pruebacertificacion.databinding.FragmentFirstBinding
import com.example.pruebacertificacion.viewmodel.ConciertosViewModel

/**
 * Fragmento que muestra la lista de conciertos.
 * @author Cintia Munoz Valdes
 */
class FirstFragment : Fragment() {
    //Instancia de Binding
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    //Instancia de view model
    private val viewModel: ConciertosViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Intancia del adapter
        val adapter = ConciertoAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        //Actualizar el adapter
        viewModel.obtenerListaConciertos().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("Listado", it.toString())
                adapter.actualizar(it)
            }
        })

        //Seleccionar un concierto
        adapter.seleccionarConcierto().observe(viewLifecycleOwner, Observer {
            it.let {
                Log.d("Seleccionado", it.toString())
            }
            //pasar id al segundo fragmento
            val bundle = Bundle().apply {
                putInt("idSelect", it.id)
            }

            //Navegar al segundo fragmento
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}