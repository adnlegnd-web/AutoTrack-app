package com.example.autotrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// 1. Agregamos la tabla Usuario::class aquí
@Database(entities = [Vehiculo::class, Mantenimiento::class, Recordatorio::class, Usuario::class], version = 2, exportSchema = false)
abstract class AutoTrackDatabase : RoomDatabase() {

    abstract fun autoTrackDao(): AutoTrackDao

    companion object {
        @Volatile
        private var INSTANCE: AutoTrackDatabase? = null

        fun getDatabase(context: Context): AutoTrackDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AutoTrackDatabase::class.java,
                    "autotrack_database"
                )
                    .fallbackToDestructiveMigration() // 2. Permite actualizar la base de datos sin errores
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}