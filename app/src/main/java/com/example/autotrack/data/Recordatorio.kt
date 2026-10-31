package com.example.autotrack.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recordatorios")
data class Recordatorio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val vehiculoId: Int,
    val concepto: String,
    val intervaloMeses: Int, // Ej: 6 meses
    val intervaloKm: Int, // Ej: 5000 km
    val estatus: String, // "Activa" o "Vencida"
    val fechaProgramada: String
)