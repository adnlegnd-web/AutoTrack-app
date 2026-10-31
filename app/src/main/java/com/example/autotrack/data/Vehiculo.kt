package com.example.autotrack.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehiculos")
data class Vehiculo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipo: String, // "Carro" o "Moto"
    val marca: String,
    val submarca: String,
    val anio: Int,
    val placa: String,
    val odometroInicial: Int,
    val tipoCombustible: String
)