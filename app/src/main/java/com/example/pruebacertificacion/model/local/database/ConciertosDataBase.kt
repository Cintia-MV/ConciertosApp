package com.example.pruebacertificacion.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebacertificacion.model.local.ConciertosDao
import com.example.pruebacertificacion.model.local.entities.DetalleConciertoLocal
import com.example.pruebacertificacion.model.local.entities.ListaConciertosLocal

/**
 * La base de datos de Room para almacenar la información de conciertos.
 * @author Cintia Munoz Valdes
 */
@Database(
    entities = [ListaConciertosLocal::class, DetalleConciertoLocal::class],
    version = 1,
    exportSchema = false
)
abstract class ConciertosDataBase : RoomDatabase() {

    //Obtiene el DAO para acceder a las operaciones de base de datos relacionadas con conciertos.
    abstract fun getConciertosDao(): ConciertosDao


    //Obtiene la instancia de la base de datos. Si no existe, se crea una nueva instancia.
    companion object {
        @Volatile
        private var INSTANCE: ConciertosDataBase? = null

        fun getDataBase(context: Context): ConciertosDataBase {
            val temInstance = INSTANCE
            if (temInstance != null) {
                return temInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ConciertosDataBase::class.java,
                    "Conciertos_bd"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}