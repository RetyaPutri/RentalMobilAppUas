package com.example.rentalmobilapp.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.rentalmobilapp.R

class SplashFragment : Fragment() {

    private val splashDelay: Long = 2000 // Waktu penundaan dalam milidetik (misalnya 2000ms = 2 detik)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        // Penundaan sebelum pindah ke fragment berikutnya atau aktivitas utama
        Handler().postDelayed({
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main)
            navController.navigate(R.id.action_SplashFragment_to_LoginFragment)
        }, splashDelay)

        return view
    }
}