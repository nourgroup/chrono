package fr.mastersid.oummadi.cronometre

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import fr.mastersid.oummadi.cronometre.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var chronometer: Chronometer

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chronometer = binding.chrono// initiate a chronometer
        startChronometer()

        // 1. ajouter ca
        if(savedInstanceState != null){
            chronometer.base =savedInstanceState.getLong("ChronoTime");
        }
        // 1. fin
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
    // 2. ajouter ca
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putLong("ChronoTime", chronometer.base)
    }
    // 2. fin
    private fun startChronometer(){
        chronometer.start()
        //viewModel.step.observe(viewLifecycleOwner, {timer ->
        val begin = chronometer.base
        var timer = (SystemClock.elapsedRealtime() - begin).toInt()
        //val elapsed = ( SystemClock.elapsedRealtime() - binding.chrono.base ).toInt()

        Log.i("chrono_test",timer.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}