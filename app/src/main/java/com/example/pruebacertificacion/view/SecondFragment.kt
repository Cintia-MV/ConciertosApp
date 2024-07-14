package com.example.pruebacertificacion.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.pruebacertificacion.databinding.FragmentSecondBinding
import com.example.pruebacertificacion.viewmodel.ConciertosViewModel

/**
 * Segundo fragmento en la navegación, muestra los detalles de un concierto.
 * @author Cintia Munoz Valdes
 */
class SecondFragment : Fragment() {

    //Instancia de binding
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    //Instancia de view model
    private val viewModel: ConciertosViewModel by activityViewModels()

    //Variable para guardar id
    private var idConcierto: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Obtener el ID del concierto seleccionado desde los argumentos del fragmento
        arguments?.let { bundle ->
            idConcierto = bundle.getInt("idSelect")
            Log.d("Seleccion SegFragment", idConcierto.toString())

        }

        // Si hay un ID de concierto, cargar los detalles del concierto desde el ViewModel
        idConcierto?.let { id ->
            viewModel.obtenerDetalleInternet(id)
        }

        // Observar los detalles del concierto desde el ViewModel
        viewModel.obtenerDetalleConcierto().observe(viewLifecycleOwner, Observer {
            Log.d("Seleccion SegFragment2", idConcierto.toString())

            val entrada = it.entradas

            //Cargar datos desde la seleccion
            Glide.with(binding.imgSegFragm).load(it.imagen).into(binding.imgSegFragm)
            binding.txtArtista.text = it.artista
            binding.txtFecha.text = "Fecha: ${it.fecha}"
            binding.txtLugar.text = "Lugar: ${it.lugar}"
            binding.txtCiudad.text = "Ciudad: ${it.ciudad}"


            //Redireccionar a url de entradas
            binding.fab.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(entrada)
                }

                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }


        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}