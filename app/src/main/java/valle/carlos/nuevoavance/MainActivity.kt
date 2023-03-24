package valle.carlos.nuevoavance

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var adapter: PeliculaAdapter?=null
    var peliculas= ArrayList<Pelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cargarPeliculas()
        adapter= PeliculaAdapter(this, peliculas)

        var gridPelis: GridView = findViewById(R.id.movies_catalog)

        gridPelis.adapter= adapter
    }

    fun cargarPeliculas() {
        peliculas.add(Pelicula("Big Hero 6",R.drawable.bighero6, "Juan Carlos", "Carlos Valle","When a devastating event befalls the city of San Fransokyo and catapults Hiro into the midst of danger, he turns to Baymax and his close friends adrenaline junkie Go Go Tomago, neatnik Wasabi, chemistry whiz Honey Lemon and fanboy Fred. Determined to uncover the mystery, Hiro transforms his friends into a band of high-tech heroes called ‘Big Hero 6.’", "B", "Aventura", "115"))
    }

    class PeliculaAdapter: BaseAdapter {
        var context: Context?=null
        var peliculas= ArrayList<Pelicula>()

        constructor(context: Context, peliculas:  ArrayList<Pelicula>){
            this.context= context
            this.peliculas= peliculas
        }

        override fun getCount(): Int {
            return peliculas.size
        }

        override fun getItem(p0: Int): Any {
            return peliculas[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var pelicula= peliculas[p0]
            var inflator= context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista= inflator.inflate(R.layout.celda, null)
            var image: ImageView = vista.findViewById(R.id.image_movie_cell)
            var title: TextView = vista.findViewById(R.id.movie_title_cell)


            image.setImageResource(pelicula.Image)
            title.setText(pelicula.titulo)

            image.setOnClickListener{
                var intento= Intent(context, datos::class.java)
                intento.putExtra("Titulo", pelicula.titulo)
                intento.putExtra("image", pelicula.Image)
                intento.putExtra("Reparto", pelicula.Reparto)
                intento.putExtra("Director", pelicula.Director)
                intento.putExtra("sinopsis", pelicula.sinopsis)
                intento.putExtra("Clasificacion", pelicula.Clasificacion)
                intento.putExtra("Duracion", pelicula.duracion)
                intento.putExtra("Categoria", pelicula.Categoria)
                context!!.startActivity(intento)
            }

            return vista
        }

    }
}
