package com.example.pruebacertificacion.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pruebacertificacion.model.local.ConciertosDao
import com.example.pruebacertificacion.model.local.entities.DetalleConciertoLocal
import com.example.pruebacertificacion.model.local.entities.ListaConciertosLocal
import com.example.pruebacertificacion.model.remote.RetrofitConciertos

/**
 * Repositorio para gestionar las operaciones relacionadas con conciertos.
 *
 * @property conciertosDao DAO para acceder a la base de datos de conciertos.
 * @author Cintia Munoz Valdes
 */
class ConciertosRepo(private val conciertosDao: ConciertosDao) {

    //Retrofit
    private val networkService = RetrofitConciertos.getRetrofit()

    //Lista de conciertos Dao
    val listaConciertosRepo = conciertosDao.obtenerListaConciertos()

    //Detalle de un concierto
    val detalleConciertosLocal = MutableLiveData<DetalleConciertoLocal>()

    //Listado
    val listaConciertoLocal = MutableLiveData<ListaConciertosLocal>()


    //Obtiene la lista de conciertos desde el servidor y la almacena en la base de datos.
    suspend fun fetchConciertos() {
        val service = kotlin.runCatching { networkService.fetchConciertosList() }

        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    Log.d("Conciertos", it.toString())
                    conciertosDao.insertarListaConciertos(conciertosInternet(it))
                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }

            service.onFailure {
                Log.d("Error", "${it.message}")
            }
        }
    }


    //Obtiene los detalles de un concierto específico por ID desde el servidor y los almacena en la base de datos.
    suspend fun fetchDetalleConcierto(id: Int): DetalleConciertoLocal? {
        val service = kotlin.runCatching { networkService.fetchDetailConcierto(id) }

        return service.getOrNull()?.body()?.let { concierto ->
            val detalleConciertoEntity = detalleInternet(concierto)
            conciertosDao.insertarDetalleConcierto(detalleConciertoEntity)
            detalleConciertoEntity

        }
    }


}