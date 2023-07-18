package com.example.rentalmobilapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "car_table")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val address: String,
    val typecar: String,
    val latitude: Double?,
    val longitude: Double?
) : Parcelable