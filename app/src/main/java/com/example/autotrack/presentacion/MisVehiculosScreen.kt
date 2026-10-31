package com.example.autotrack.presentacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autotrack.logic.VehiculoViewModel
import com.example.autotrack.data.Vehiculo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisVehiculosScreen(viewModel: VehiculoViewModel, onVolverClick: () -> Unit) {
    // Observamos los datos; si cambian en la base de datos, la pantalla se actualiza sola
    val vehiculos by viewModel.vehiculos.collectAsState()

    // Ejecutamos la consulta al abrir esta pantalla
    LaunchedEffect(Unit) {
        viewModel.cargarVehiculos()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Vehículos", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onVolverClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E293B))
            )
        },
        containerColor = Color(0xFFF1F5F9)
    ) { paddingValues ->
        if (vehiculos.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("No tienes vehículos registrados aún.", color = Color.Gray, fontSize = 16.sp)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
                items(vehiculos) { vehiculo ->
                    VehiculoCard(vehiculo)
                }
            }
        }
    }
}

@Composable
fun VehiculoCard(vehiculo: Vehiculo) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("${vehiculo.marca} ${vehiculo.submarca}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                Badge(containerColor = if (vehiculo.tipo == "Carro") Color(0xFF3B82F6) else Color(0xFF10B981)) {
                    Text(vehiculo.tipo, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Año: ${vehiculo.anio} | Placa: ${vehiculo.placa.ifEmpty { "N/A" }}", color = Color.DarkGray)
            Text("Odómetro: ${vehiculo.odometroInicial} km", color = Color.DarkGray)
            Text("Combustible: ${vehiculo.tipoCombustible}", color = Color.DarkGray)
        }
    }
}