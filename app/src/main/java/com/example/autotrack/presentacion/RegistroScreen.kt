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
fun RegistroScreen(
    viewModel: AuthViewModel,
    onRegistroExitoso: () -> Unit,
    onVolverClick: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E293B))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Crear Cuenta", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 32.dp))

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

        OutlinedTextField(
            value = confirmarPassword, onValueChange = { confirmarPassword = it },
            label = { Text("Confirmar contraseña", color = Color.LightGray) },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(focusedContainerColor = Color(0xFF334155), unfocusedContainerColor = Color(0xFF334155), focusedTextColor = Color.White, unfocusedTextColor = Color.White),
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
        )

        if (mensajeError.isNotEmpty()) {
            Text(text = mensajeError, color = Color(0xFFEF4444), modifier = Modifier.padding(bottom = 16.dp))
        }

        Button(
            onClick = {
                if (correo.isBlank() || password.isBlank()) {
                    mensajeError = "Llena todos los campos"
                } else if (password != confirmarPassword) {
                    mensajeError = "Las contraseñas no coinciden"
                } else {
                    scope.launch {
                        val exito = viewModel.registrarCuenta(correo, password)
                        if (exito) onRegistroExitoso() else mensajeError = "El correo ya está registrado"
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Registrarse", fontSize = 16.sp, color = Color.White)
        }

        TextButton(onClick = onVolverClick, modifier = Modifier.padding(top = 16.dp)) {
            Text("Ya tengo cuenta. Iniciar sesión", color = Color(0xFF94A3B8))
        }
    }
}