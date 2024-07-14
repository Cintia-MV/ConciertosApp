package com.example.pruebacertificacion.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad local para almacenar lista de conciertos en la base de datos.
 *
 * @property id Identificador único del concierto.
 * @property artista Nombre del artista.
 * @property fecha Fecha del concierto.
 * @property lugar Lugar donde se realizará cabo el concierto.
 * @property ciudad Ciudad donde se realiza el concierto.
 * @property imagen URL o ruta de la imagen del concierto.
 * @property entradas URL para las entradas del concierto.
 *
 * @author Cintia Munoz Valdes
 */
@Entity(tableName = "lista_conciertos_tabla")
data class ListaConciertosLocal(

    @PrimaryKey
    val id: Int,
    val artista: String,
    val fecha: String,
    val lugar: String,
    val ciudad: String,
    val imagen: String,
    val entradas: String

)
