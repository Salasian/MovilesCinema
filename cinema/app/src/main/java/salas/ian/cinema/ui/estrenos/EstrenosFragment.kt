package salas.ian.cinema.ui.estrenos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import salas.ian.cinema.databinding.FragmentEstrenosBinding
import salas.ian.cinema.databinding.FragmentInicioBinding
import salas.ian.cinema.ui.ubicacion.UbicacionActivity

class EstrenosFragment : Fragment() {

    private var _binding: FragmentEstrenosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(EstrenosViewModel::class.java)

        _binding = FragmentEstrenosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.botonUbicacion.setOnClickListener {
            val intent = Intent(getActivity(), UbicacionActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}