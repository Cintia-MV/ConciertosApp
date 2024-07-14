package com.example.pruebacertificacion.model

import com.example.pruebacertificacion.model.local.entities.DetalleConciertoLocal
import com.example.pruebacertificacion.model.local.entities.ListaConciertosLocal
import com.example.pruebacertificacion.model.remote.internet.DetalleConciertoInternet
import com.example.pruebacertificacion.model.remote.internet.ListaConciertosInternet

/**
 * Convierte una lista de conciertos de Internet a una lista de conciertos locales.
 *
 * @param listaConciertos La lista de conciertos de internet.
 * @return La lista de conciertos locales convertida.
 * @author Cintia Munoz Valdes
 */
fun conciertosInternet(listaConciertos: List<ListaConciertosInternet>): List<ListaConciertosLocal> {
    return listaConciertos.map {
        ListaConciertosLocal(
            id = it.id,
            artista = it.artista,
            fecha = it.fecha,
            lugar = it.lugar,
            ciudad = it.ciudad,
            imagen = it.imagen,
            entradas = it.entradas
        )
    }
}

/**
 * Convierte los detalles de un concierto de Internet a los detalles de un concierto local.
 *
 * @param detalle Los detalles del concierto de internet.
 * @return Los detalles del concierto local convertidos.
 */
fun detalleInternet(detalle: DetalleConciertoInternet): DetalleConciertoLocal {
    return DetalleConciertoLocal(
        id = detalle.id,
        artista = detalle.artista,
        fecha = detalle.fecha,
        lugar = detalle.lugar,
        ciudad = detalle.ciudad,
        imagen = detalle.imagen,
        entradas = detalle.entradas
    )
}