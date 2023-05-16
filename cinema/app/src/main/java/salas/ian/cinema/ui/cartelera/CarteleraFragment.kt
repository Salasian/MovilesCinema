package salas.ian.cinema.ui.cartelera

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.ktx.Firebase
import salas.ian.cinema.R
import salas.ian.cinema.databinding.FragmentCarteleraBinding
import salas.ian.cinema.ui.ubicacion.UbicacionActivity

class CarteleraFragment : Fragment() {
    var peliculas= ArrayList<Pelicula>()
    private var _binding: FragmentCarteleraBinding? = null
    private lateinit var db: FirebaseFirestore

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

            db= FirebaseFirestore.getInstance()

            _binding = FragmentCarteleraBinding.inflate(inflater, container, false)
            val root: View = binding.root

            cargarPeliculas()

            return root
        }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cargarPeliculas() {
        db.collection("pelicula").get().addOnSuccessListener { resultado->
            for(documento in resultado){
                val peli= documento.toObject(Pelicula::class.java)
                peliculas.add(peli)
                Log.d("Pelicula Agregada:", "$peli")
            }
            Log.d("Peliculas", "$peliculas")

            var gridPelis = binding.moviesCatalog
            var adaptador = PeliculaAdapter(requireContext(), peliculas)
            gridPelis.adapter = adaptador
        }
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

            Glide.with(context!!)
                .load(pelicula.Image)
                .into(image)
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
                intento.putExtra("Duracion", pelicula.Duracion)
                intento.putExtra("Categoria", pelicula.Categoria1)
                intento.putExtra("Rating", pelicula.Rating)
                vista.context.startActivity(intento)

            }

            return vista
        }

    }
}