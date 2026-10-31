package com.example.autotrack.presentacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.autotrack.logic.VehiculoViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun AltaVehiculoScreen(viewModel: VehiculoViewModel, onGuardadoExitoso: () -> Unit) {
    var tipo by remember { mutableStateOf("Carro") }
    var marca by remember { mutableStateOf("") }
    var submarca by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var placa by remember { mutableStateOf("") }
    var odometro by remember { mutableStateOf("") }
    var combustible by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Registrar Vehículo",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Selector sencillo para Carro o Moto
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            RadioButton(selected = tipo == "Carro", onClick = { tipo = "Carro" })
            Text("Carro", modifier = Modifier.padding(top = 12.dp, end = 16.dp))
            RadioButton(selected = tipo == "Moto", onClick = { tipo = "Moto" })
            Text("Moto", modifier = Modifier.padding(top = 12.dp))
        }

        OutlinedTextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text("Marca (Ej. Nissan)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = submarca,
            onValueChange = { submarca = it },
            label = { Text("Modelo/Submarca (Ej. Versa)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = anio,
            onValueChange = { anio = it },
            label = { Text("Año") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = placa,
            onValueChange = { placa = it },
            label = { Text("Placa (Opcional)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = odometro,
            onValueChange = { odometro = it },
            label = { Text("Kilometraje actual") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = combustible,
            onValueChange = { combustible = it },
            label = { Text("Tipo de combustible (Ej. Gasolina)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                viewModel.guardarVehiculo(tipo, marca, submarca, anio, placa, odometro, combustible)
                onGuardadoExitoso()
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Guardar Vehículo")
        }
    }
}