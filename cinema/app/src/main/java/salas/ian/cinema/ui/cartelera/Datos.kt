package salas.ian.cinema.ui.cartelera

import android.content.ContentProvider.CallingIdentity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
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


        val gridView: GridView = findViewById(R.id.gridView)
        val items = listOf(
            "Datos",
            "Horarios",
            "Calificación"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> startActivity(Intent(this, Datos::class.java))
                1 -> startActivity(Intent(this, Horarios::class.java))
                2 -> startActivity(Intent(this, ratingCelda::class.java))
            }
        }


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

    class GridAdapter(private val context: Context, private val items: List<String>) : BaseAdapter() {

        override fun getCount(): Int {
            return items.size
        }

        override fun getItem(position: Int): Any {
            return items[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var view = convertView
            val holder: ViewHolder

            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.gridlayout, parent, false)
                holder = ViewHolder(view)
                view.tag = holder
            } else {
                holder = view.tag as ViewHolder
            }

            val item = getItem(position) as String
            holder.title.text = item

            return view
        }

        private class ViewHolder(view: View) {
            val title: TextView = view.findViewById(R.id.item_title)
        }
    }
}
