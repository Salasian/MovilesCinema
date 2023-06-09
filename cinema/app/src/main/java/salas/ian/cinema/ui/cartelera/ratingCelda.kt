package salas.ian.cinema.ui.cartelera

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import salas.ian.cinema.R

class ratingCelda : AppCompatActivity() {

    var opiniones= ArrayList<Opinion>()
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_pelicula)

        db= FirebaseFirestore.getInstance()

        agregarOpinion()


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

        val editText: EditText= findViewById(R.id.edit)
        editText.setOnClickListener{
            val intent= Intent(this, agregarOpinion::class.java)
            startActivity(intent)
            }

    }

    fun agregarOpinion(){
        db.collection("comentarios").get().addOnSuccessListener { resultado->
            for(documento in resultado){
                val op= documento.toObject(Opinion::class.java)
                opiniones.add(op)
                Log.d("Opinión Agregada:", "$op")
            }
            Log.d("Opiniones", "$opiniones")

            var listView: ListView= findViewById(R.id.lista)
            var adaptador: OpinionAdapter= OpinionAdapter(this, opiniones)
            listView.adapter= adaptador
        }
    }

    class OpinionAdapter: BaseAdapter{
        var context: Context?= null
        var opiniones= ArrayList<Opinion>()

        constructor(context: Context, opiniones:  ArrayList<Opinion>){
            this.context= context
            this.opiniones= opiniones
        }

        override fun getCount(): Int {
            return opiniones.size
        }

        override fun getItem(p0: Int): Any {
            return opiniones[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var opinion= opiniones[p0]
            var inflator= LayoutInflater.from(context)
            var vista= inflator.inflate(R.layout.activity_rating_celda, null)
            var nombre= vista.findViewById(R.id.nombre1) as TextView
            var fecha= vista.findViewById(R.id.fecha) as TextView
            var opinion11= vista.findViewById(R.id.opinion11) as TextView
            var rating: RatingBar= vista.findViewById(R.id.ratingbar)

            nombre.setText(opinion.nombre)
            fecha.setText(opinion.fecha)
            opinion11.setText(opinion.comentario)
            rating.setRating(opinion.calificacion)

            return vista
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