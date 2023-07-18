package com.example.rentalmobilapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentalmobilapp.R
import com.example.rentalmobilapp.application.CarApp
import com.example.rentalmobilapp.databinding.FragmentFirstBinding
import kotlin.math.log

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val carViewModel: CarViewModel by viewModels {
        CarViewModelFactory((applicationContext as CarApp).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CarListAdapter {car ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(car)
            findNavController().navigate(action)
        }
        binding.dataRecyclerView.adapter = adapter
        binding.dataRecyclerView.layoutManager = LinearLayoutManager(context)
        carViewModel.allCar.observe(viewLifecycleOwner) { Car ->
            Log.d("Ubah", Car.count().toString())
            Car.let {
                if (Car.isEmpty()) {
                    binding.EmptytextView.visibility = View.VISIBLE
                    binding.illustrationImageView.visibility = View.VISIBLE
                } else {
                    binding.EmptytextView.visibility = View.GONE
                    binding.illustrationImageView.visibility = View.GONE
                }
                adapter.submitList(Car)
            }
        }

        binding.addFAB.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(null)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}