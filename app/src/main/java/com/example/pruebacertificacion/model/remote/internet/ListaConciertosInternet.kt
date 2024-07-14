package com.example.pruebacertificacion.model.remote.internet

/**
 * POJO que representa una lista de conciertos obtenidos de una fuente remota.
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
data class ListaConciertosInternet(
    val id: Int,
    val artista: String,
    val fecha: String,
    val lugar: String,
    val ciudad: String,
    val imagen: String,
    val entradas: String
)
