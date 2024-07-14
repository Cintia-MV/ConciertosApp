package com.example.pruebacertificacion.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebacertificacion.databinding.ItemListBinding
import com.example.pruebacertificacion.model.local.entities.ListaConciertosLocal

/**
 * Adaptador para mostrar la lista de conciertos en un RecyclerView.
 */
class ConciertoAdapter : RecyclerView.Adapter<ConciertoAdapter.ConciertosRecyclerView>() {
    //Referencias para el adapter
    private var listaConciertos = listOf<ListaConciertosLocal>()
    private val conciertoSeleccionado = MutableLiveData<ListaConciertosLocal>()


    //Actualizar el adapter
    fun actualizar(lista: List<ListaConciertosLocal>) {
        listaConciertos = lista
        notifyDataSetChanged()
    }


    //Seleccionar un concierto
    fun seleccionarConcierto(): LiveData<ListaConciertosLocal> = conciertoSeleccionado


    //Funciones de view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConciertosRecyclerView {
        return ConciertosRecyclerView(ItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listaConciertos.size

    override fun onBindViewHolder(holder: ConciertosRecyclerView, position: Int) {
        val concierto = listaConciertos[position]
        holder.bind(concierto)
    }


    //Clase interna
    inner class ConciertosRecyclerView(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(concierto: ListaConciertosLocal) {
            Glide.with(binding.img).load(concierto.imagen).centerCrop().into(binding.img)
            binding.textArtista.text = concierto.artista
            binding.textFecha.text = concierto.fecha
            binding.textCiudad.text = concierto.ciudad

            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            conciertoSeleccionado.value = listaConciertos[adapterPosition]
        }

    }


}