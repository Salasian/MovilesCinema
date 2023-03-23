package salas.ian.cinema.ui.cartelera

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import salas.ian.cinema.R
import salas.ian.cinema.databinding.FragmentCarteleraBinding

class CarteleraFragment : Fragment() {
    var adapter: PeliculaAdapter?=null
    var peliculas= ArrayList<Pelicula>()

    private var _binding: FragmentCarteleraBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(CarteleraViewModel::class.java)

        _binding = FragmentCarteleraBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            var vista= inflator.inflate(R.layout.detalles_pelicula, null)
            var image: ImageView = vista.findViewById(R.id.imagen)
            var title: TextView = vista.findViewById(R.id.titulo)
            var clasificacion: TextView = vista.findViewById(R.id.clasificacion)
            var duracion: TextView = vista.findViewById(R.id.duracion)
            var categoria: TextView = vista.findViewById(R.id.categoria)
            var director: TextView = vista.findViewById(R.id.director)
            var reparto: TextView = vista.findViewById(R.id.reparto)
            var sinopsis: TextView = vista.findViewById(R.id.Sinopsis)

            image.setImageResource(pelicula.Image)
            title.setText(pelicula.titulo)



            return vista
        }

    }
}