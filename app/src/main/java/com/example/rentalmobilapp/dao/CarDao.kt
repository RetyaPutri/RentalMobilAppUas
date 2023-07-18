package com.example.rentalmobilapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.rentalmobilapp.model.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Query("SELECT * FROM 'car_table' ORDER BY name ASC")
    fun getAllCar():Flow<List<Car>>

    @Insert
    suspend fun insertCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)

    @Update fun updateCar(car: Car)

}

annotation class Login
