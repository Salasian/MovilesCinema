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
import android.widget.RatingBar
import android.widget.TextView
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
        peliculas.add(Pelicula("Godzilla vs Kong",
            R.drawable.king_kong, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro", "Carlos Valle","\" Sinopsis Actual", "B", "Acción", "",  4.5f, "115"))
        peliculas.add(Pelicula("Caos: El Inicio",
            R.drawable.caos_el_inicio, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro","Carlos Valle","\"Sinopsis Actual.’", "B", "Aventura", "Ciencia F.",4.5f, "115"))
        peliculas.add(Pelicula("El Protector",
            R.drawable.el_protector, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro","Carlos Valle","\"Sinopsis Actual.’", "B", "Acción", "",4.5f, "115"))
        peliculas.add(Pelicula("UUUPS! 2 La Aventura Continúa",
            R.drawable.uuups, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro","Carlos Valle","\"Sinopsis Actual’", "B", "Animación", "",4.5f, "115"))
        peliculas.add(Pelicula("El Día del Fin del Mundo",
            R.drawable.el_dia_del_fin_del_mundo, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro","Carlos Valle","\"Sinopsis Actual’", "B", "Acción", "Thriller",4.5f, "115"))
        peliculas.add(Pelicula("El Tunel",
            R.drawable.el_tunel, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro","Carlos Valle","\"Sinopsis Actual’", "B", "Thriller", "Acción",4.5f, "115"))
        peliculas.add(Pelicula("Tom y Jerry",
            R.drawable.tom_y_jerry, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro","Carlos Valle","\"Sinopsis Actual’", "B", "Animación", "",4.5f, "115"))
        peliculas.add(Pelicula("Pinocho",
            R.drawable.pinocho, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro","Carlos Valle","\"Sinopsis Actual’", "B", "Fantasía", "Drama",4.5f, "115"))
        peliculas.add(Pelicula("Juega Conmigo",
            R.drawable.juega_conmigo, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro","Carlos Valle","\"Sinopsis Actual’", "B", "Terror", "",4.5f, "115"))
        peliculas.add(Pelicula("Mujer Maravilla",
            R.drawable.mujer_maravilla, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro","Carlos Valle","\"Sinopsis Actual’", "B", "Acción", "", 4.5f, "115"))
        peliculas.add(Pelicula("El Cazador de Mounstros",
            R.drawable.cazador_de_mostruos, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro","Carlos Valle","\"Sinopsis Actual’", "B", "Terror", "Fantasía",4.5f, "115"))
        peliculas.add(Pelicula("Dime Cuando Tú",
            R.drawable.dime_cuando_tu, "Juan Carlos", "Alex Gael", "Juan Sotelo", "Cesar Haro","Carlos Valle","\"Sinopsis Actual’", "B", "Drama", "Comedia",4.5f, "115"))

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
            var categoria1: TextView= vista.findViewById(R.id.categoria1)
            var categoria2: TextView= vista.findViewById(R.id.categoria2)
            var rating: RatingBar= vista.findViewById(R.id.ratingbar)

            image.setImageResource(pelicula.Image)
            title.setText(pelicula.titulo)
            categoria1.setText(pelicula.Categoria1)
            categoria2.setText(pelicula.Categoria2)
            if(categoria2.getText().toString().isEmpty()){
                categoria2.setVisibility(View.GONE)
            }else{
                categoria2.setVisibility(View.VISIBLE)
            }
            rating.setRating(pelicula.Rating)

            image.setOnClickListener{
                var intento= Intent(vista.context, Datos::class.java)
                intento.putExtra("Titulo", pelicula.titulo)
                intento.putExtra("image", pelicula.Image)
                intento.putExtra("Reparto", pelicula.Reparto)
                intento.putExtra("Reparto1", pelicula.Reparto1)
                intento.putExtra("Reparto2", pelicula.Reparto2)
                intento.putExtra("Reparto3", pelicula.Reparto3)
                intento.putExtra("Director", pelicula.Director)
                intento.putExtra("sinopsis", pelicula.Sinopsis)
                intento.putExtra("Clasificacion", pelicula.Clasificacion)
                intento.putExtra("Duracion", pelicula.duracion)
                intento.putExtra("Categoria", pelicula.Categoria1)
                vista.context.startActivity(intento)

            }

            return vista
        }

    }
}