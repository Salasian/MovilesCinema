package salas.ian.cinema.ui.cartelera

import java.io.Serializable


data class Pelicula(
    var titulo: String="", var Image:String="", var Reparto: String="", var Reparto1: String="",
    var Reparto2: String="", var Reparto3: String="", var Director: String="",
    var Sinopsis: String="", var Clasificacion: String="", var Categoria1: String="",
    var Categoria2: String="",  var Rating: Float=0.0f, var Duracion: String=""): Serializable