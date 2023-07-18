package com.example.rentalmobilapp.repository

import com.example.rentalmobilapp.dao.CarDao
import com.example.rentalmobilapp.model.Car
import kotlinx.coroutines.flow.Flow

class CarRepository (private val carDao: CarDao){
    val allCars: Flow<List<Car>> = carDao.getAllCar()

    suspend fun insertCar(car: Car){
        carDao.insertCar(car)
    }

    suspend fun deleteCar(car: Car){
        carDao.deleteCar(car)
    }

    suspend fun updateCar(car: Car){
        carDao.updateCar(car)
    }
}