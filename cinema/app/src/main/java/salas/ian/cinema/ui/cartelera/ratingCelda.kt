package salas.ian.cinema.ui.cartelera

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import salas.ian.cinema.R

class ratingCelda : AppCompatActivity() {

    var opiniones= ArrayList<Opinion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_pelicula)

        agregarOpinion()

        var listView: ListView= findViewById(R.id.lista)

        var adaptador: OpinionAdapter= OpinionAdapter(this, opiniones)
        listView.adapter= adaptador
    }

    fun agregarOpinion(){
        opiniones.add(Opinion("Alex Gael", "30/03/23", "Me gusto"))
        opiniones.add(Opinion("Juan Sotelo", "30/03/23", "Muy Buena"))
        opiniones.add(Opinion("Cesar Haro", "30/03/23", "Muy mala"))
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

            nombre.setText(opinion.Nombre)
            fecha.setText(opinion.Fecha)
            opinion11.setText(opinion.Opinion)

            return vista
        }
    }
}