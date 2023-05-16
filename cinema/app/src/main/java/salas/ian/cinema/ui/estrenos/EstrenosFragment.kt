package salas.ian.cinema.ui.estrenos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import salas.ian.cinema.databinding.FragmentEstrenosBinding
import salas.ian.cinema.databinding.FragmentInicioBinding
import salas.ian.cinema.ui.cartelera.Pelicula
import salas.ian.cinema.ui.ubicacion.UbicacionActivity
import salas.ian.cinema.R

class EstrenosFragment : Fragment() {
    var estrenos= ArrayList<Estrenos>()
    private var _binding: FragmentEstrenosBinding? = null
    private lateinit var db: FirebaseFirestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = EstrenosFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dashboardViewModel =
            ViewModelProvider(this).get(EstrenosViewModel::class.java)

        _binding = FragmentEstrenosBinding.inflate(inflater, container, false)
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
        db.collection("estrenos").get().addOnSuccessListener { resultado->
            for(documento in resultado){
                val est= documento.toObject(Estrenos::class.java)
                estrenos.add(est)
                Log.d("Estreno Agregado:", "$est")
            }
            Log.d("Estrenos", "$estrenos")

            var listCines = binding.estrenosList
            var adaptador = EstrenosFragment.EstrenoAdapter(requireContext(), estrenos)
            listCines.adapter = adaptador
        }
    }

    class EstrenoAdapter: BaseAdapter {
        var context: Context?=null
        var estrenos= ArrayList<Estrenos>()

        constructor(context: Context, estrenos:  ArrayList<Estrenos>){
            this.context= context
            this.estrenos= estrenos
        }

        override fun getCount(): Int {
            return estrenos.size
        }

        override fun getItem(p0: Int): Any {
            return estrenos[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var est= estrenos[p0]
            var inflator= context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista= inflator.inflate(R.layout.activity_estrenos_celda, null)
            var image: ImageView = vista.findViewById(R.id.imagenPelicula) as ImageView
            var titulo: TextView = vista.findViewById(R.id.titulo)
            var title: TextView = vista.findViewById(R.id.title)
            var duracion: TextView = vista.findViewById(R.id.duracion)
            var sinopsis: TextView= vista.findViewById(R.id.sinopsis)
            var fecha: TextView= vista.findViewById(R.id.fecha)
            var categoria: TextView= vista.findViewById(R.id.categoria)

            Glide.with(context!!)
                .load(est.Image)
                .into(image)
            titulo.setText(est.titulo)
            title.setText(est.title)
            duracion.setText(est.duracion)
            sinopsis.setText(est.sinopsis)
            fecha.setText(est.fecha)
            categoria.setText(est.categoria)

            return vista
            }
        }
}