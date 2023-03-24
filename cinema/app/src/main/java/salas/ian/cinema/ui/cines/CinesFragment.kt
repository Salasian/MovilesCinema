package salas.ian.cinema.ui.cines

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import salas.ian.cinema.R

class CinesFragment : Fragment() {

    companion object {
        fun newInstance() = CinesFragment()
    }

    private lateinit var viewModel: CinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cines, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CinesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}