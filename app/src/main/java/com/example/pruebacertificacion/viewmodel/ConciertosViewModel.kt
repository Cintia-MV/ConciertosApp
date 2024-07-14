package com.example.pruebacertificacion.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pruebacertificacion.model.ConciertosRepo
import com.example.pruebacertificacion.model.local.database.ConciertosDataBase
import com.example.pruebacertificacion.model.local.entities.DetalleConciertoLocal
import com.example.pruebacertificacion.model.local.entities.ListaConciertosLocal
import kotlinx.coroutines.launch

/**
 * ViewModel para manejar la lógica de la pantalla de conciertos.
 * @author Cintia Munoz Valdes
 * @param aplicacion La aplicación actual.
 */
class ConciertosViewModel(aplicacion: Application) : AndroidViewModel(aplicacion) {

    //Conexion repositorio
    private val repo: ConciertosRepo

    //Entidad
    private val detalleConciertosLocal = MutableLiveData<DetalleConciertoLocal>()

    init {
        val bd = ConciertosDataBase.getDataBase(aplicacion)
        val conciertoDao = bd.getConciertosDao()
        repo = ConciertosRepo(conciertoDao)

        //Llamar a fetchLista
        viewModelScope.launch {
            repo.fetchConciertos()
        }
    }


    //Listado elementos
    fun obtenerListaConciertos(): LiveData<List<ListaConciertosLocal>> = repo.listaConciertosRepo

    //Detalle de concierto
    fun obtenerDetalleConcierto(): LiveData<DetalleConciertoLocal> = detalleConciertosLocal


    //Obtiene los detalles del concierto desde la API y los guarda en el LiveData.
    fun obtenerDetalleInternet(id: Int) = viewModelScope.launch {
        val detalleConcierto = repo.fetchDetalleConcierto(id)
        detalleConcierto?.let {
            detalleConciertosLocal.postValue(it)
        }
    }


}