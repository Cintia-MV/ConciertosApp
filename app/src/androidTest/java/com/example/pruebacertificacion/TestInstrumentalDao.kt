package com.example.pruebacertificacion

import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.platform.app.InstrumentationRegistry
import com.example.pruebacertificacion.model.local.ConciertosDao
import com.example.pruebacertificacion.model.local.database.ConciertosDataBase
import com.example.pruebacertificacion.model.local.entities.ListaConciertosLocal
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestInstrumentalDao {

    //Variable de prueba
    private lateinit var db: ConciertosDataBase
    private lateinit var conciertosDao: ConciertosDao

    @Before
    fun setUp(){
        //Inicializacion base de datos y dao
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, ConciertosDataBase::class.java).build()
        conciertosDao = db.getConciertosDao()
    }

    @After
    fun tearDown(){
        //Cierre base de datos
        db.close()
    }

    @Test
    fun testDao() = runBlocking {
        //Creacion datos de prueba
        val listaConciertos = listOf(
            ListaConciertosLocal(1, "Journey", "15/06/2024", "Movistar Arena", "Santiago", "img1", "punto ticket"),
            ListaConciertosLocal(2, "Lenny Kravitz", "04/07/2024", "Estadio nacional", "Ñuñoa", "img2", "punto ticket")
        )

        //Insertar datos de prueba en la base de datos
        conciertosDao.insertarListaConciertos(listaConciertos)

        //Observar LiveData en el hilo principal
        runOnUiThread{
            //Obtener livedata de todos los conciertos
            val conciertosLiveData = conciertosDao.obtenerListaConciertos()

            //Crear el observador de la lista de conciertos
            val observadorConciertos = Observer<List<ListaConciertosLocal>> {listadoConciertos ->
                ViewMatchers.assertThat(listadoConciertos, CoreMatchers.not(emptyList()))

                assertEquals(2, listadoConciertos.size)
            }

            //Observar los conciertos
            conciertosLiveData.observeForever(observadorConciertos)

            //Sacar el observador despues de las aserciones
            conciertosLiveData.removeObserver(observadorConciertos)
        }












    }












}