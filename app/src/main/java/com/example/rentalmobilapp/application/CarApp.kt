package com.example.rentalmobilapp.application

import android.app.Application
import com.example.rentalmobilapp.repository.CarRepository

class CarApp: Application() {
    val database by lazy { CarDatabase.getDatabase(this)}
    val repository by lazy { CarRepository(database.CarDao()) }
}