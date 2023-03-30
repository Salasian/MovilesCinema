package salas.ian.cinema.ui.cartelera

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import salas.ian.cinema.R


class Datos: AppCompatActivity() {
    var peliculas = ArrayList<Pelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalles_pelicula)

       var menuGrid: GridView= findViewById(R.id.menu)
        var adaptador= CarteleraFragment.PeliculaAdapter(this, peliculas)


        menuGrid.adapter= adaptador


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
    internal class CustomAdapter(private val mContext: Context) :
        BaseAdapter() {
        override fun getCount(): Int {
            return 3
        }
        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
            val button: Button
            if (convertView == null) {
                button = Button(mContext)
                button.layoutParams =
                    AbsListView.LayoutParams(GridView.AUTO_FIT, 150)
            } else {
                button = convertView as Button
            }

            if (position == 0) {
                button.text = "Datos"
            } else if (position == 1) {
                button.text = "Horarios"
            } else if (position == 2) {
                button.text = "Calificaciones"
            }

            button.setOnClickListener {
                if (position == 0) {
                    var intento= Intent(button.context, Datos::class.java)
                    button.context.startActivity(intento)
                } else if (position == 1) {
                    // Cargar pantalla 2
                } else if (position == 2) {
                    var intento= Intent(button.context, ratingCelda::class.java)
                    button.context.startActivity(intento)
                }
            }
            return button
        }
    }
}