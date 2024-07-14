package com.example.pruebacertificacion.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Configurar y obtener una instancia de Retrofit para realizar llamadas a la API de conciertos.
 * @author Cintia Munoz Valdes
 */
class RetrofitConciertos {

    companion object{

        private const val BASE_URL = "https://jp-conciertos.onrender.com/"

        //Instancia de Retrofit configurada.
        lateinit var retrofitInstance: Retrofit

        //Obtiene la implementación de [ConciertosApi] configurada con Retrofit.
        fun getRetrofit(): ConciertosApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ConciertosApi::class.java)
        }
    }
}