package com.example.pruebacertificacion

import com.example.pruebacertificacion.model.local.entities.ListaConciertosLocal
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TestUnitarioListaConciertosEntidad {

    //Variable para la entidad
    private lateinit var listaConciertos : ListaConciertosLocal

    //Funcion que se ejecuta antes de cada prueba
    @Before
    fun setUp(){
        //Entidad con valores de prueba
        listaConciertos = ListaConciertosLocal(
            id = 19,
            artista = "Queen",
            fecha = "09/07/2024",
            lugar = "TRM",
            ciudad = "Talca",
            imagen = "img1",
            entradas = "url"


        )
    }


    //Funcion que se ejecuta despues de cada prueba
    @After
    fun tearDown(){
        //En este caso no hay nada que ejecutar despues de la prueba, se agregar por buena practica
    }


    //Funcion que verifica el constructor de la entidad
    @Test
    fun testListaConciertos(){
        // Verificar que los valores asignados en el constructor sean correctos
        assert(listaConciertos.id == 19)
        assert(listaConciertos.artista == "Queen")
        assert(listaConciertos.fecha == "09/07/2024")
        assert(listaConciertos.lugar == "TRM")
        assert(listaConciertos.ciudad == "Talca")
        assert(listaConciertos.imagen == "img1")
        assert(listaConciertos.entradas == "url")
    }


}