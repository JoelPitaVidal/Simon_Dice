package com.example.simon_dice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simon_dice.Datos.secuenciaGenerada
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Función Composable que representa la interfaz de usuario principal del juego.
@Composable
fun UI(model: ViewModel) {
    // Llama a la función Greeting para mostrar el contenido principal de la UI.
    Greeting(myViewModel = model)
}

// Función Composable que muestra el saludo y los elementos de la UI del juego.
@Composable
fun Greeting(modifier: Modifier = Modifier, myViewModel: ViewModel) {
    // Obtiene el contexto actual de la aplicación.
    val context = LocalContext.current
    // Define un color verde claro para el fondo.
    val lightGreen = Color(0xFFCCFFCC)
    // Estados para los colores de los botones, usando mutableStateOf para que sean observables.
    val redButtonColor = remember { mutableStateOf(Colors.RED.color) }
    val blueButtonColor = remember { mutableStateOf(Colors.BLUE.color) }
    val greenButtonColor = remember { mutableStateOf(Colors.GREEN.color) }
    val yellowButtonColor = remember { mutableStateOf(Colors.YELLOW.color) }

    // Función suspendida para colorear la secuencia de botones.
    suspend fun colorearSecuencia() {
        // Itera sobre la secuencia generada.
        for (i in secuenciaGenerada) {
            delay(300) // Pausa de 300ms antes de cada color.
            // Cambia el color del botón según el valor de 'i'.
            when (i) {
                Colors.RED.id -> {
                    // Cambia al color presionado.
                    redButtonColor.value = Colors.RED.colorPressed
                    // Pausa de 1 segundo.
                    delay(1000)
                    // Vuelve al color original.
                    redButtonColor.value = Colors.RED.color
                }
                Colors.BLUE.id -> {
                    blueButtonColor.value = Colors.BLUE.colorPressed
                    delay(1000)
                    blueButtonColor.value = Colors.BLUE.color
                }
                Colors.GREEN.id -> {
                    greenButtonColor.value = Colors.GREEN.colorPressed
                    delay(1000)
                    greenButtonColor.value = Colors.GREEN.color
                }
                Colors.YELLOW.id -> {
                    yellowButtonColor.value = Colors.YELLOW.colorPressed
                    delay(1000)
                    yellowButtonColor.value = Colors.YELLOW.color
                }
            }
        }
        // Cambia el estado del juego a "jugando" después de mostrar la secuencia.
        myViewModel.jugandoJuego()
    }

    // Columna principal para organizar los elementos de la UI.
    Column(
        // Alineación horizontal al centro.
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            // Ocupa el tamaño disponible.
            .fillMaxSize()
            // Aplica el color de fondo verde claro.
            .background(lightGreen)
    ) {
        // Texto para mostrar el título del juego.
        Text(
            text = "SIMON DICE ",
            // Tamaño de la fuente.
            fontSize = 38.sp,
            // Padding vertical.
            modifier = Modifier.padding(vertical = 100.dp)
        )
        // Fila para organizar los botones de colores en una cuadrícula 2x2.
        Row {
            // Columna para los botones rojo y azul.
            Column {
                // Botón rojo.
                Button(
                    // Acción al hacer clic.
                    onClick = { myViewModel.click(Colors.RED.id, context) },
                    modifier = Modifier
                        // Padding alrededor del botón.
                        .padding(10.dp)
                        // Tamaño del botón.
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        // Color del botón.
                        containerColor = redButtonColor.value,
                        // Color del texto del botón.
                        contentColor = Color.White
                    )
                ) {
                    // Texto del botón.
                    Text(text = Colors.RED.nombre)
                }
                // Botón azul.
                Button(
                    onClick = { myViewModel.click(Colors.BLUE.id, context) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blueButtonColor.value,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = Colors.BLUE.nombre)
                }
            }
            // Columna para los botones verde y amarillo.
            Column {
                // Botón verde.
                Button(
                    onClick = { myViewModel.click(Colors.GREEN.id, context) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = greenButtonColor.value,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = Colors.GREEN.nombre)
                }
                // Botón amarillo.
                Button(
                    onClick = { myViewModel.click(Colors.YELLOW.id, context) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = yellowButtonColor.value,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = Colors.YELLOW.nombre)
                }
            }
        }
        // Espacio flexible para empujar el botón "START" hacia abajo.
        Spacer(modifier = Modifier.weight(1f))
        // Coroutine scope para lanzar la corrutina.
        val coroutineScope = rememberCoroutineScope()
        // Botón "START".
        TextButton(
            onClick = {
                coroutineScope.launch {
                    myViewModel.generarSecuencia()
                    colorearSecuencia()
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .size(300.dp, 100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            // Texto del botón con la ronda actual.
            Text(text = "START ronda: " + Datos.ronda.value)
        }
    }
}