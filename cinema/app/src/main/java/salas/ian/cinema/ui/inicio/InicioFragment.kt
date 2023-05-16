package salas.ian.cinema.ui.inicio

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import salas.ian.cinema.R
import salas.ian.cinema.databinding.ActivityMainBinding
import salas.ian.cinema.databinding.FragmentInicioBinding
import salas.ian.cinema.ui.MovieAdapter
import salas.ian.cinema.ui.MovieModel
import salas.ian.cinema.ui.cartelera.ratingCelda
import salas.ian.cinema.ui.ubicacion.UbicacionActivity




class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    var ofertas= ArrayList<Oferta>()
    var carrusel= ArrayList<Oferta>()
    private lateinit var db: FirebaseFirestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        db= FirebaseFirestore.getInstance()
        agregarOfertas()
        val homeViewModel =
            ViewModelProvider(this).get(InicioViewModel::class.java)

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.botonUbicacion.setOnClickListener {
            val intent = Intent(getActivity(), UbicacionActivity::class.java)
            startActivity(intent)
        }

        val movieList = ArrayList<MovieModel>()
        movieList.add(MovieModel(R.drawable.godzillavskong,""))
        movieList.add(MovieModel(R.drawable.godzillavskong,""))
        movieList.add(MovieModel(R.drawable.godzillavskong,""))

        val adapter = MovieAdapter(movieList)

        binding.apply {
            carouselRecyclerview.adapter=adapter
            carouselRecyclerview.setAlpha(true)
            carouselRecyclerview.setInfinite(true)
            carouselRecyclerview.setFlat(true)
        }

        return root
    }



    fun agregarOfertas(){
        var ofertasDB=db.collection("inicio")
        Log.d("Ofertas", "${ofertasDB.toString()}")
        ofertasDB.document("x8TPx1HfmRvrt9NfXbvv").get().addOnSuccessListener { document ->
            Log.d("Ofertas", "${document.data?.get("oferta3")}")
            Glide.with(requireContext())
                .load(document.data?.get("oferta1"))
                .into(binding.oferta1)

            Glide.with(requireContext())
                .load(document.data?.get("oferta2"))
                .into(binding.oferta2)

            Glide.with(requireContext())
                .load(document.data?.get("oferta3"))
                .into(binding.oferta3)

            Glide.with(requireContext())
                .load(document.data?.get("oferta4"))
                .into(binding.oferta4)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}