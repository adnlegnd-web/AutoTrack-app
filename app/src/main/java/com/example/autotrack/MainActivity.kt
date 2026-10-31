package com.example.autotrack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.autotrack.data.AutoTrackDatabase
import com.example.autotrack.logic.AuthViewModel
import com.example.autotrack.logic.VehiculoViewModel
import com.example.autotrack.presentacion.AltaVehiculoScreen
import com.example.autotrack.presentacion.HomeScreen
import com.example.autotrack.presentacion.LoginScreen
import com.example.autotrack.presentacion.RegistroScreen
import com.example.autotrack.ui.theme.AutoTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AutoTrackDatabase.getDatabase(this)
        val dao = database.autoTrackDao()

        // Fabricamos el VehiculoViewModel
        val vehiculoFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = VehiculoViewModel(dao) as T
        }
        val vehiculoViewModel = ViewModelProvider(this, vehiculoFactory)[VehiculoViewModel::class.java]

        // Fabricamos el AuthViewModel
        val authFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = AuthViewModel(dao) as T
        }
        val authViewModel = ViewModelProvider(this, authFactory)[AuthViewModel::class.java]

        setContent {
            AutoTrackTheme {
                val navController = rememberNavController()
                val context = LocalContext.current

                NavHost(navController = navController, startDestination = "login") {

                    composable("login") {
                        LoginScreen(
                            viewModel = authViewModel,
                            onLoginExitoso = {
                                navController.navigate("home") { popUpTo("login") { inclusive = true } }
                            },
                            onCrearCuentaClick = { navController.navigate("registro") },
                            onOlvidePasswordClick = {
                                // Por ahora mostraremos un mensaje emergente para la recuperación
                                Toast.makeText(context, "Módulo de recuperación en construcción", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }

                    composable("registro") {
                        RegistroScreen(
                            viewModel = authViewModel,
                            onRegistroExitoso = {
                                Toast.makeText(context, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                                navController.popBackStack() // Regresa al login para que inicie sesión
                            },
                            onVolverClick = { navController.popBackStack() }
                        )
                    }

                    composable("home") {
                        HomeScreen(
                            onAgregarVehiculoClick = { navController.navigate("alta_vehiculo") },
                            onMisVehiculosClick = { navController.navigate("mis_vehiculos") } // <-- Agregamos esto
                        )
                    }

                    composable("mis_vehiculos") {
                        com.example.autotrack.presentacion.MisVehiculosScreen(
                            viewModel = vehiculoViewModel,
                            onVolverClick = { navController.popBackStack() }
                        )
                    }

                    composable("alta_vehiculo") {
                        AltaVehiculoScreen(
                            viewModel = vehiculoViewModel,
                            onGuardadoExitoso = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}