package com.example.autotrack.logic

import androidx.lifecycle.ViewModel
import com.example.autotrack.data.AutoTrackDao
import com.example.autotrack.data.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthViewModel(private val dao: AutoTrackDao) : ViewModel() {

    // Devuelve 'true' si el usuario y contraseña son correctos
    suspend fun iniciarSesion(correo: String, contrasena: String): Boolean {
        return withContext(Dispatchers.IO) {
            val usuario = dao.validarLogin(correo, contrasena)
            usuario != null
        }
    }

    // Devuelve 'true' si la cuenta se creó, o 'false' si el correo ya existía
    suspend fun registrarCuenta(correo: String, contrasena: String): Boolean {
        return withContext(Dispatchers.IO) {
            val existe = dao.comprobarCorreoExistente(correo)
            if (existe == null) {
                dao.registrarUsuario(Usuario(correo = correo, contrasena = contrasena))
                true
            } else {
                false
            }
        }
    }
}