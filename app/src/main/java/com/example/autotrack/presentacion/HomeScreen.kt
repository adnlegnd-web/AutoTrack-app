package com.example.autotrack.presentacion

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(onAgregarVehiculoClick: () -> Unit, onMisVehiculosClick: () -> Unit) { // <-- Cambio aquí
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F5F9))
            .padding(16.dp)
    ) {
        Text(
            text = "Inicio",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F172A),
            modifier = Modifier.padding(top = 40.dp, bottom = 24.dp)
        )

        MenuCard(
            titulo = "Agregar Vehículo / Moto",
            descripcion = "Registra una nueva unidad a tu cuenta",
            colorFondo = Color(0xFF3B82F6), // Azul
            onClick = onAgregarVehiculoClick
        )

        MenuCard(
            titulo = "Mis Vehículos",
            descripcion = "Ver flotilla y estado general",
            colorFondo = Color(0xFF10B981),
            onClick = onMisVehiculosClick
        )

        MenuCard(
            titulo = "Recordatorios",
            descripcion = "Alertas de mantenimiento pendientes",
            colorFondo = Color(0xFFF59E0B),
            onClick = { /* Futura pantalla */ }
        )
    }
}

@Composable
fun MenuCard(titulo: String, descripcion: String, colorFondo: Color, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colorFondo),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = titulo, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = descripcion, color = Color.White.copy(alpha = 0.8f), modifier = Modifier.padding(top = 4.dp))
        }
    }
}