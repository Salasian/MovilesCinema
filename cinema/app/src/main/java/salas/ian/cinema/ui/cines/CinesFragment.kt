package salas.ian.cinema.ui.cines

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import salas.ian.cinema.R
import salas.ian.cinema.databinding.FragmentCinesBinding
import salas.ian.cinema.databinding.FragmentEstrenosBinding
import salas.ian.cinema.databinding.FragmentInicioBinding
import salas.ian.cinema.ui.ubicacion.UbicacionActivity

class CinesFragment : Fragment() {
    var cines= ArrayList<Cine>()
    private var _binding: FragmentCinesBinding? = null
    private lateinit var db: FirebaseFirestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CinesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dashboardViewModel =
            ViewModelProvider(this).get(CinesViewModel::class.java)

        _binding = FragmentCinesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        db= FirebaseFirestore.getInstance()

        binding.botonUbicacion.setOnClickListener {
            val intent = Intent(getActivity(), UbicacionActivity::class.java)
            startActivity(intent)
        }

        cargarCines()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cargarCines() {
        db.collection("cines").get().addOnSuccessListener { resultado->
            for(documento in resultado){
                val cinema= documento.toObject(Cine::class.java)
                cines.add(cinema)
                Log.d("Cine Agregado:", "$cinema")
            }
            Log.d("Cines", "$cines")

            var listCines = binding.lista
            var adaptador = CinesFragment.CineAdapter(requireContext(), cines)
            listCines.adapter = adaptador
        }
    }

    class CineAdapter: BaseAdapter{
        var context: Context?=null
        var cines= ArrayList<Cine>()

        constructor(context: Context, cines:  ArrayList<Cine>){
            this.context= context
            this.cines= cines
        }

        override fun getCount(): Int {
            return cines.size
        }

        override fun getItem(p0: Int): Any {
            return cines[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var cine= cines[p0]
            var inflator= context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista= inflator.inflate(R.layout.activity_cines_celda, null)
            var image: ImageView = vista.findViewById(R.id.cinema)
            var nombreCine: TextView = vista.findViewById(R.id.nombreCine)
            var tipoSala: TextView = vista.findViewById(R.id.salasCine)
            var calificaciones: RatingBar = vista.findViewById(R.id.ratingbarCine)
            var direccion: TextView= vista.findViewById(R.id.direccionCine)

            Glide.with(context!!)
                .load(cine.Image)
                .into(image)
            nombreCine.setText(cine.nombreCine)
            calificaciones.setRating(cine.calificaciones)
            direccion.setText(cine.direccion)
            tipoSala.setText(cine.tipoSala)

            return vista
            }
        }
}