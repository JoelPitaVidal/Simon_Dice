package com.example.simon_dice

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.mutableStateOf

// Declaración de un objeto para almacenar los datos del juego como un singleton.
object Datos {
    // Número de ronda actual, observable mediante mutableStateOf.
    var ronda = mutableStateOf(0)
    // Lista para almacenar la secuencia de colores/números generada aleatoriamente.
    val secuenciaGenerada = mutableListOf<Int>()
    // Lista para almacenar la secuencia de colores/números introducida por el usuario.
    val secuenciaIntroducida = mutableListOf<Int>()
    // Cadena para almacenar el mensaje que se muestra al usuario.
    var mensajeMostrado = ""
}
// Clase enum para definir los colores utilizados en el juego.
enum class Colors(val id: Int, val nombre: String, val color: Color, val colorPressed: Color) {
    // Cada color tiene un ID, nombre, color por defecto y color al presionar.
    RED(0, "Rojo", Color(0xFFE57373), Color(0xFFB71C1C)),
    BLUE(1, "Azul", Color(0xFF64B5F6), Color(0xFF0D47A1)),
    GREEN(2, "Verde", Color(0xFF81C784), Color(0xFF1B5E20)),
    YELLOW(3, "Amarillo", Color(0xFFFFF176), Color(0xFFF57F17));
}
// Enum class que representa los diferentes estados del juego.
enum class Estados(val start_activo: Boolean, val boton_activo: Boolean) {
    // Cada estado define si el botón de inicio y los botones de color están activos.
    ESPERANDO(start_activo = true, boton_activo = false), // Esperando para empezar
    GENERANDO(start_activo = false, boton_activo = false), // Generando la secuencia
    JUGANDO(start_activo = false, boton_activo = true);   // Jugando al juego
}