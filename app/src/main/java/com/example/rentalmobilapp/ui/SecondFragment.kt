package com.example.rentalmobilapp.ui

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rentalmobilapp.R
import com.example.rentalmobilapp.application.CarApp
import com.example.rentalmobilapp.databinding.FragmentSecondBinding
import com.example.rentalmobilapp.model.Car
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener{

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val carViewModel: CarViewModel by viewModels {
        CarViewModelFactory((applicationContext as CarApp).repository)
    }
    private val args: SecondFragmentArgs by navArgs()
    private var car: Car? = null
    private lateinit var mMap: GoogleMap
    private var currentLatLng: LatLng? = null
    private lateinit var  fusedLocationProviderClient: FusedLocationProviderClient

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       car = args.car
        if (car != null){
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = ("Ubah")
            binding.nameEditText.setText(car?.name)
            binding.addressEditText.setText(car?.address)
            binding.typecarEditText.setText(car?.typecar)
        }

        //binding google map
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        checkPermission()

        val name = binding.nameEditText.text
        val address = binding.addressEditText.text
        val typecar = binding.typecarEditText.text

        binding.saveButton.setOnClickListener {
            if (name.isEmpty()){
                Toast.makeText(context, "nama tidak boleh kosong", Toast.LENGTH_SHORT).show()

            } else if (address.isEmpty()) {
                Toast.makeText(context, "alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (typecar.isEmpty()) {
                    Toast.makeText(context, "jenis mobil tidak boleh kosong", Toast.LENGTH_SHORT).show()
                } else {
                if (car == null) {
                    val car = Car(0, name.toString(), address.toString(), typecar.toString(),currentLatLng?.latitude, currentLatLng?.longitude)
                    carViewModel.insert(car)
                } else {
                    val car =
                        Car(car?.id!!, name.toString(), address.toString(), typecar.toString(),currentLatLng?.latitude, currentLatLng?.longitude)
                    carViewModel.update(car)
                }
            }

                findNavController().popBackStack()//untuk dismiss halaman ini

        }
        binding.deleteButton.setOnClickListener {
            car?.let {carViewModel.delete(it) }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //implement drag marker

        val uiSettings = mMap.uiSettings
        uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerDragListener(this)
    }

    override fun onMarkerDrag(p0: Marker) {

    }
    override fun onMarkerDragEnd(marker: Marker) {
        val newPosition = marker.position
        currentLatLng = LatLng(newPosition.latitude, newPosition.longitude)
        Toast.makeText(context, currentLatLng.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerDragStart(p0: Marker) {

    }

    private fun checkPermission(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(applicationContext)
        if (ContextCompat.checkSelfPermission(
            applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            getCurrentLocation()
        }else {
            Toast.makeText(applicationContext, "Akses lokasi ditolak", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCurrentLocation() {
        // ngecekj jika permission tidak disetujui maka akan berhenti di kondisi if
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            return
        }

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    var latLng = LatLng(location.latitude, location.longitude)
                    currentLatLng = latLng
                    var title = "Marker"


                    if (car != null){
                        title = car?.name.toString()
                        val newCurrentLocation = LatLng(car?.latitude!!, car?.longitude!!)
                        latLng = newCurrentLocation
                    }
                    val markerOptions = MarkerOptions()
                        .position (latLng)
                        .title(title)
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.rental))
                    mMap.addMarker(markerOptions)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                }
            }
    }
}