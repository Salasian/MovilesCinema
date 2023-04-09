package salas.ian.cinema.ui.cartelera

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import salas.ian.cinema.R

class Horarios: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.horarios_pelicula)

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

