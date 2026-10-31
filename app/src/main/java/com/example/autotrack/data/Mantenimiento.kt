package com.example.autotrack.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mantenimientos")
data class Mantenimiento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val vehiculoId: Int, // Sirve para saber a qué vehículo pertenece este servicio
    val concepto: String, // Ej: "Cambio de aceite sintético"
    val fecha: String,
    val kilometrajeAlMomento: Int,
    val piezasSustituidas: String,
    val costoTotal: Double
)