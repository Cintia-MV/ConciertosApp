package com.example.pruebacertificacion.model.remote

import com.example.pruebacertificacion.model.remote.internet.DetalleConciertoInternet
import com.example.pruebacertificacion.model.remote.internet.ListaConciertosInternet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interfaz para las llamadas a la API de conciertos.
 * @author Cintia Munoz Valdes
 */
interface ConciertosApi {

    //Obtiene la lista de conciertos desde el servidor.
    @GET("conciertos")
    suspend fun fetchConciertosList(): Response<List<ListaConciertosInternet>>

    //Obtiene los detalles de un concierto específico por ID desde el servidor.
    @GET("conciertos/{id}")
    suspend fun fetchDetailConcierto(@Path("id") id: Int): Response<DetalleConciertoInternet>
}