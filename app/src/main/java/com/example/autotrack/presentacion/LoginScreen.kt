package com.example.autotrack.presentacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autotrack.logic.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginExitoso: () -> Unit,
    onCrearCuentaClick: () -> Unit,
    onOlvidePasswordClick: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF1E293B)).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("AutoTrack", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        Text("Tu bitácora vehicular digital", color = Color(0xFF94A3B8), modifier = Modifier.padding(bottom = 48.dp))

        OutlinedTextField(
            value = correo, onValueChange = { correo = it },
            label = { Text("Correo electrónico", color = Color.LightGray) },
            colors = TextFieldDefaults.colors(focusedContainerColor = Color(0xFF334155), unfocusedContainerColor = Color(0xFF334155), focusedTextColor = Color.White, unfocusedTextColor = Color.White),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = password, onValueChange = { password = it },
            label = { Text("Contraseña", color = Color.LightGray) },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(focusedContainerColor = Color(0xFF334155), unfocusedContainerColor = Color(0xFF334155), focusedTextColor = Color.White, unfocusedTextColor = Color.White),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        if (mensajeError.isNotEmpty()) {
            Text(text = mensajeError, color = Color(0xFFEF4444), modifier = Modifier.padding(bottom = 16.dp))
        }

        Button(
            onClick = {
                scope.launch {
                    val exito = viewModel.iniciarSesion(correo, password)
                    if (exito) onLoginExitoso() else mensajeError = "Correo o contraseña incorrectos"
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Iniciar Sesión", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onOlvidePasswordClick) {
            Text("¿Olvidaste tu contraseña?", color = Color(0xFF94A3B8))
        }

        TextButton(onClick = onCrearCuentaClick) {
            Text("¿No tienes cuenta? Crear una", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}