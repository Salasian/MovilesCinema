package salas.ian.cinema.ui.cartelera

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import salas.ian.cinema.R
import salas.ian.cinema.databinding.FragmentCarteleraBinding

class CarteleraFragment : Fragment() {
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

        cargarPeliculas()
        var gridPelis: GridView = binding.moviesCatalog
        var adaptador= PeliculaAdapter(requireContext(), peliculas)


        gridPelis.adapter= adaptador

        return root
    }

    fun cargarPeliculas() {
        peliculas.add(Pelicula("Big Hero 6",
            R.drawable.bighero6, "Juan Carlos", "Carlos Valle","\"When a devastating event befalls the city of San Fransokyo and catapults Hiro into the midst of danger, he turns to Baymax and his close friends adrenaline junkie Go Go Tomago, neatnik Wasabi, chemistry whiz Honey Lemon and fanboy Fred. Determined to uncover the mystery, Hiro transforms his friends into a band of high-tech heroes called ‘Big Hero 6.’", "B", "Aventura", "115"))
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
            var vista= inflator.inflate(R.layout.celda, null)
            var image: ImageView = vista.findViewById(R.id.image_movie_cell)
            var title: TextView = vista.findViewById(R.id.movie_title_cell)

            image.setImageResource(pelicula.Image)
            title.setText(pelicula.titulo)

            image.setOnClickListener{
                var intento= Intent(vista.context, Datos::class.java)
                intento.putExtra("Titulo", pelicula.titulo)
                intento.putExtra("image", pelicula.Image)
                intento.putExtra("Reparto", pelicula.Reparto)
                intento.putExtra("Director", pelicula.Director)
                intento.putExtra("sinopsis", pelicula.Sinopsis)
                intento.putExtra("Clasificacion", pelicula.Clasificacion)
                intento.putExtra("Duracion", pelicula.duracion)
                intento.putExtra("Categoria", pelicula.Categoria)
                vista.context.startActivity(intento)

            }

            return vista
        }

    }
}