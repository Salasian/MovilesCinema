package salas.ian.cinema.ui.inicio

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import salas.ian.cinema.R
import salas.ian.cinema.databinding.FragmentInicioBinding
import salas.ian.cinema.ui.MovieAdapter
import salas.ian.cinema.ui.MovieModel
import salas.ian.cinema.ui.ubicacion.UbicacionActivity


class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(InicioViewModel::class.java)

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val movieList = ArrayList<MovieModel>()
        movieList.add(MovieModel(R.drawable.godzillavskong,""))
        movieList.add(MovieModel(R.drawable.godzillavskong,""))
        movieList.add(MovieModel(R.drawable.godzillavskong,""))

        val adapter = MovieAdapter(movieList)
        binding.botonUbicacion.setOnClickListener {
            val intent = Intent(getActivity(), UbicacionActivity::class.java)
            startActivity(intent)
        }


        binding.apply {
            carouselRecyclerview.adapter=adapter
            carouselRecyclerview.setAlpha(true)
            carouselRecyclerview.setInfinite(true)
            carouselRecyclerview.setFlat(true)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}