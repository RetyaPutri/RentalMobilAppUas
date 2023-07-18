package com.example.rentalmobilapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.rentalmobilapp.model.Car
import com.example.rentalmobilapp.repository.CarRepository
import kotlinx.coroutines.launch

class CarViewModel(private val repository: CarRepository): ViewModel() {
    val allCar: LiveData<List<Car>> = repository.allCars.asLiveData()

    fun insert (car: Car) = viewModelScope.launch {
        repository.insertCar(car)
    }

    fun delete (car: Car) = viewModelScope.launch {
        repository.deleteCar(car)
    }

    fun update (car: Car) = viewModelScope.launch {
        repository.updateCar(car)
    }
}
class CarViewModelFactory(private val repository: CarRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((CarViewModel::class.java))) {
            return CarViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown viewModel class")
    }
}
