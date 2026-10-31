package com.example.autotrack.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AutoTrackDao {

    // --- CONSULTAS PARA VEHÍCULOS ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarVehiculo(vehiculo: Vehiculo): Long

    @Query("SELECT * FROM vehiculos")
    fun obtenerTodosLosVehiculos(): List<Vehiculo>

    // --- CONSULTAS PARA MANTENIMIENTOS (BITÁCORA) ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarMantenimiento(mantenimiento: Mantenimiento): Long

    @Query("SELECT * FROM mantenimientos WHERE vehiculoId = :idVehiculo ORDER BY id DESC")
    fun obtenerHistorialPorVehiculo(idVehiculo: Int): List<Mantenimiento>

    // --- CONSULTAS PARA RECORDATORIOS ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarRecordatorio(recordatorio: Recordatorio): Long

    @Query("SELECT * FROM recordatorios WHERE estatus = 'Activa'")
    fun obtenerRecordatoriosActivos(): List<Recordatorio>

    // --- CONSULTAS PARA USUARIOS (LOGIN) ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registrarUsuario(usuario: Usuario): Long

    @Query("SELECT * FROM usuarios WHERE correo = :correo AND contrasena = :contrasena LIMIT 1")
    fun validarLogin(correo: String, contrasena: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    fun comprobarCorreoExistente(correo: String): Usuario?
}