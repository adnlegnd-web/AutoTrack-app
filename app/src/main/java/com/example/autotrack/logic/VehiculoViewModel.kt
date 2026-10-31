package com.example.autotrack.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autotrack.data.AutoTrackDao
import com.example.autotrack.data.Vehiculo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VehiculoViewModel(private val dao: AutoTrackDao) : ViewModel() {

    // Variable que almacena la lista de vehículos y avisa a la pantalla cuando hay cambios
    private val _vehiculos = MutableStateFlow<List<Vehiculo>>(emptyList())
    val vehiculos: StateFlow<List<Vehiculo>> = _vehiculos

    // Función para leer la base de datos
    fun cargarVehiculos() {
        viewModelScope.launch(Dispatchers.IO) {
            val lista = dao.obtenerTodosLosVehiculos()
            _vehiculos.value = lista
        }
    }

    fun guardarVehiculo(
        tipo: String, marca: String, submarca: String,
        anio: String, placa: String, odometro: String, combustible: String
    ) {
        val anioNumerico = anio.toIntOrNull() ?: 0
        val odometroNumerico = odometro.toIntOrNull() ?: 0

        val nuevoVehiculo = Vehiculo(
            tipo = tipo, marca = marca, submarca = submarca, anio = anioNumerico,
            placa = placa, odometroInicial = odometroNumerico, tipoCombustible = combustible
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insertarVehiculo(nuevoVehiculo)
            cargarVehiculos() // Actualizamos la lista después de guardar
        }
    }
}