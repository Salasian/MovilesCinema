package salas.ian.cinema.ui.cartelera

import java.io.Serializable

data class Opinion (
    var nombre: String="", var fecha: String="", var comentario: String="",
    var calificacion: Float=0.0f): Serializable
