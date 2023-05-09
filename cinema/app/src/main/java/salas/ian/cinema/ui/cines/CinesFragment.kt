package salas.ian.cinema.ui.cines

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import salas.ian.cinema.R
import salas.ian.cinema.databinding.FragmentCinesBinding
import salas.ian.cinema.databinding.FragmentEstrenosBinding
import salas.ian.cinema.databinding.FragmentInicioBinding
import salas.ian.cinema.ui.ubicacion.UbicacionActivity

class CinesFragment : Fragment() {

    private var _binding: FragmentCinesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CinesFragment()
    }

    private lateinit var viewModel: CinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCinesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.botonUbicacion.setOnClickListener {
            val intent = Intent(getActivity(), UbicacionActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[CinesViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}