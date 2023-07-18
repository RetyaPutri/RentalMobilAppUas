package com.example.rentalmobilapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.rentalmobilapp.R

class LoginFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Inisialisasi elemen-elemen tampilan
        usernameEditText = view.findViewById(R.id.usernameEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        loginButton = view.findViewById(R.id.loginButton)

        // Setel onClickListener untuk buttonLogin
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Panggil fungsi login(username, password) untuk melakukan proses login
            login(username, password)
        }

        return view
    }

    private fun login(username: String, password: String) {
        // Implementasikan logika login di sini, misalnya memanggil API login

        // Contoh hasil login sukses
        if (username == "admin" && password == "admin123") {
            // Tampilkan pesan login sukses
            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main)
            navController.navigate(R.id.action_LoginFragment_to_HomeFragment)
            // Lakukan navigasi ke halaman berikutnya setelah login
            // Misalnya menggunakan NavHostFragment atau Intent
        } else {
            // Tampilkan pesan login gagal
            Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }
}
