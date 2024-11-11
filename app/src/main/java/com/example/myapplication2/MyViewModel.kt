package com.example.myapplication2

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

// Definimos la clase MyViewModel que hereda de ViewModel
class MyViewModel(): ViewModel() {

    // Etiqueta para el logcat, útil para depuración
    private val TAG_LOG = "miDebug"

    // Variable que almacena un número entero mutable, inicializado en 0
    // Usamos mutable, ya que queremos que este valor pueda cambiar
    private var _numbers = mutableIntStateOf(0)

    // Lista mutable que contendrá los números mezclados
    private var numerosMezclados = mutableListOf(0)

    // Inicializamos variables cuando se instancia la clase
    init {
        Log.d(TAG_LOG, "Inicializamos ViewModel") // Mensaje de depuración al inicializar
    }

    // Función que se llama al iniciar, cambia el estado de 'haStarted' a true
    fun onStart() {
        Datos.haStarted.value = true
    }

    // Función que se llama cuando hay una coincidencia
    private fun onMatch() {
        Datos.haStarted.value = false // Cambia 'haStarted' a false
        Datos.hasWon.value = !Datos.hasWon.value // Cambia el estado a 'ganado'
    }

    /**
     * Función para crear un número entero aleatorio
     */
    fun crearRandom() {
        // Generamos un número aleatorio entre 0 y 3
        _numbers.intValue = (0..3).random()

        Log.d(TAG_LOG, "creamos random ${_numbers.intValue}") // Log del número aleatorio creado
        actualizarNumero(_numbers.intValue) // Actualizamos el número en Datos

        // Creamos una lista de números del 0 al 3 y la mezclamos
        numerosMezclados = (0..3).toList().shuffled().toMutableList()
        Log.d(TAG_LOG, "$numerosMezclados") // Log de la lista mezclada
    }

    // Función para comparar el número aleatorio con el número en la lista mezclada
    fun compararRandom(n: Int) {
        // Log de inicio de comparación
        Log.d(TAG_LOG, "comparamos numeros")

        if (Datos.numero != numerosMezclados[n]) {
            // Log si los números no son iguales
            Log.d(TAG_LOG, "numero desigual")
        } else {
            // Log si los números son iguales
            Log.d(TAG_LOG, "numero igual")

            // Llama a la función onMatch si hay coincidencia
            onMatch()
        }
    }

    // Función para actualizar el número en Datos
    private fun actualizarNumero(numero: Int) {
        Log.d(TAG_LOG, "actualizamos numero en Datos") // Log de actualización
        Datos.numero = numero // Actualiza el número en la clase Datos
    }
}