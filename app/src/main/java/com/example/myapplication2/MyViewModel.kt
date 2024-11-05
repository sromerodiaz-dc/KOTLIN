package com.example.myapplication2

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel(): ViewModel() {

    // etiqueta para logcat
    private val TAG_LOG = "miDebug"

    // este va a ser nuestra lista para la secuencia random
    // usamos mutable, ya que la queremos modificar
    private var _numbers = mutableIntStateOf(0)

    val numerosMutables: MutableList<Int> = MutableList(4){0}


    // inicializamos variables cuando instanciamos
    init {
        Log.d(TAG_LOG, "Inicializamos ViewModel")
    }

    /**
     * crear entero random
     */
    fun crearRandom() {
        _numbers.intValue = (0..3).random()
        for (n in 0 until 4) {
            numerosMutables[n] = (0..3).random()
        }
        Log.d(TAG_LOG, "creamos random ${_numbers.intValue}")
        Log.d(TAG_LOG,"Correspondencia: $numerosMutables")
        actualizarNumero(_numbers.intValue)
    }

    // Estado para controlar el resultado del juego
    var hasWon = mutableStateOf(false)

    fun compararRandom(n: Int) {
        Log.d(TAG_LOG, "comparamos numeros")
        if (Datos.numero != numerosMutables[n]) {
            Log.d(TAG_LOG, "numero desigual, reset")
            crearRandom()
        } else {
            Log.d(TAG_LOG, "numero igual")
            hasWon.value = true // Cambiar el estado a 'ganado'
        }
    }

    private fun actualizarNumero(numero: Int) {
        Log.d(TAG_LOG, "actualizamos numero en Datos")
        Datos.numero = numero
    }
}