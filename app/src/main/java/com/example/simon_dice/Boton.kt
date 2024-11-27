package com.example.simon_dice

import androidx.compose.ui.graphics.Color

enum class Boton(val numero: Int, val color: Color, val textColor: Color) {
    AMARILLO(1, Color.Yellow, Color.Black),
    ROJO(2, Color.Red, Color.White),
    AZUL(3, Color.Blue, Color.White),
    VERDE(4, Color.Green, Color.White),
    START(5, Color.Black, Color.White)
}