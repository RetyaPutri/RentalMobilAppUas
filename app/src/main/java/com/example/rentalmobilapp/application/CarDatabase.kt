package com.example.rentalmobilapp.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rentalmobilapp.dao.CarDao
import com.example.rentalmobilapp.model.Car

@Database(entities = [Car::class], version = 2, exportSchema = false)
abstract class CarDatabase: RoomDatabase() {
    abstract fun CarDao(): CarDao

    companion object{
        private var INSTANCE: CarDatabase? = null

        // migrasi databes versi 1 ke 2 , karena ada perubahan table tadi
        private val migration1To2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE car_table ADD COLUMN latitude Double DEFAULT 0.0")
                database.execSQL("ALTER TABLE car_table ADD COLUMN longitude Double DEFAULT 0.0")
            }
        }

        fun getDatabase (context: Context): CarDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                   context.applicationContext,
                   CarDatabase::class.java,
                   "car_database_88"
                )
                    .addMigrations(migration1To2)
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}