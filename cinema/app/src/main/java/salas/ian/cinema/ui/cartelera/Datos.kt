package salas.ian.cinema.ui.cartelera

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import salas.ian.cinema.R

class Datos: AppCompatActivity() {
    var peliculas = ArrayList<Pelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalles_pelicula)


        val bundle = intent.extras
        var ns = 0
        var id = -1
        var title = ""

        var sinopsis: TextView = findViewById(R.id.Sinopsis)
        var titulo: TextView = findViewById(R.id.titulo)
        var imagen: ImageView = findViewById(R.id.imagen)
        var director: TextView = findViewById(R.id.director)
        var reparto: TextView = findViewById(R.id.reparto)
        var reparto1: TextView= findViewById(R.id.reparto1)
        var reparto2: TextView= findViewById(R.id.reparto2)
        var reparto3: TextView= findViewById(R.id.reparto3)
        var duracion: TextView = findViewById(R.id.duracion)
        var clasificacion: TextView = findViewById(R.id.clasificacion)
        var categoria: TextView = findViewById(R.id.categoria)


        if (bundle != null) {


            title = bundle.getString("Titulo")!!
            sinopsis.setText(bundle.getString("sinopsis"))
            director.setText(bundle.getString("Director"))
            reparto.setText(bundle.getString("Reparto"))
            reparto1.setText(bundle.getString("Reparto1"))
            reparto2.setText(bundle.getString("Reparto2"))
            reparto3.setText(bundle.getString("Reparto3"))
            clasificacion.setText(bundle.getString("Clasificacion"))
            duracion.setText(bundle.getString("Duracion"))
            categoria.setText(bundle.getString("Categoria"))
            titulo.setText(bundle.getString("Titulo"))
            imagen.setImageResource(bundle.getInt("image"))

        }

    }
}