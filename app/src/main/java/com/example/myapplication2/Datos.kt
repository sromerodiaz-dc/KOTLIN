package com.example.myapplication2

import androidx.compose.ui.graphics.Color

/**
 * Clase para almacenar los datos del juego
 */
object Datos {
    var numero = 0
}

/**
 * Colores utilizados
 */

enum class Colores(val color: Color, val txt: String) {
    CLASE_ROJO(color = Color.Red, txt = "roxo"),
    CLASE_VERDE(color = Color.Green, txt = "verde"),
    CLASE_AZUL(color = Color.Blue, txt = "azul"),
    CLASE_AMARILLO(color = Color.Yellow, txt = "melo")
}