package com.example.pruebacertificacion.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebacertificacion.model.local.entities.DetalleConciertoLocal
import com.example.pruebacertificacion.model.local.entities.ListaConciertosLocal


/**
 * DAO (Data Access Object) para acceder a las entidades relacionadas con conciertos en la base de datos.
 * @author Cintia Munoz Valdes
 */
@Dao
interface ConciertosDao {

    //Insertar lista de conciertos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarListaConciertos(listaConcietos: List<ListaConciertosLocal>)

    //Seleccionar listado de conciertos
    @Query("SELECT * FROM lista_conciertos_tabla ORDER BY id ASC")
    fun obtenerListaConciertos(): LiveData<List<ListaConciertosLocal>>

    //Insertar detalle de concierto
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarDetalleConcierto(concierto: DetalleConciertoLocal)

    //Seleccionar detalle
    @Query("SELECT * FROM detalle_tabla WHERE id= :id")
    fun obtenerDetalleConcierto(id: Int): LiveData<DetalleConciertoLocal>
}