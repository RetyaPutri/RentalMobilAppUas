package com.example.rentalmobilapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.rentalmobilapp.R
import com.example.rentalmobilapp.databinding.FragmentHomeBinding
import com.example.rentalmobilapp.model.Car

class HomeFragment : Fragment() {

    private lateinit var inforental: Button
    private lateinit var reservasi: Button
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        inforental = binding.inforental
        reservasi = binding.reservasi

        inforental.setOnClickListener {
            navigateToInfoFragment()
        }

        reservasi.setOnClickListener {
            navigateToFirstFragment()
        }

        return binding.root
    }

    private fun navigateToInfoFragment() {
        Toast.makeText(activity, "Add Info", Toast.LENGTH_SHORT).show()
        val navController = findNavController()
        navController.navigate(R.id.action_HomeFragment_to_InfoFragment)
    }

    private fun navigateToFirstFragment() {
        Toast.makeText(activity, "Add Info", Toast.LENGTH_SHORT).show()
        val navController = findNavController()
        navController.navigate(R.id.action_HomeFragment_to_FirstFragment)
    }

//    private fun inforental() {
//
//        Toast.makeText(activity, "Add Info", Toast.LENGTH_SHORT).show()
//        val navController = Navigation.findNavController(
//            requireActivity(),
//            R.id.nav_host_fragment_content_main
//        )
//        navController.navigate(R.id.action_HomeFragment_to_InfoFragment)
//    }

//    private fun reservasi() {
//
//        Toast.makeText(activity, "Add Reservation", Toast.LENGTH_SHORT).show()
//        val navController = Navigation.findNavController(
//            requireActivity(),
//            R.id.nav_host_fragment_content_main
//        )
//        navController.navigate(R.id.action_HomeFragment_to_SecondFragment)
//    }
}
