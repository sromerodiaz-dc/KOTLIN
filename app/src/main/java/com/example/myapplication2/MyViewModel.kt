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
    var numerosMezclados = mutableListOf(0)

    // inicializamos variables cuando instanciamos
    init {
        Log.d(TAG_LOG, "Inicializamos ViewModel")

    }

    /**
     * crear entero random
     */
    fun crearRandom() {
        _numbers.intValue = (0..3).random()
        Log.d(TAG_LOG, "creamos random ${_numbers.intValue}")
        actualizarNumero(_numbers.intValue)

        numerosMezclados = (0..3).toList().shuffled().toMutableList()
        Log.d(TAG_LOG, "$numerosMezclados")
    }

    fun compararRandom(n: Int) {
        Log.d(TAG_LOG, "comparamos numeros")
        if (Datos.numero != numerosMezclados[n]) {
            Log.d(TAG_LOG, "numero desigual, reset")
            crearRandom()
        } else {
            Log.d(TAG_LOG, "numero igual")
            Datos.hasWon.value = !Datos.hasWon.value // Cambiar el estado a 'ganado'
        }
    }

    private fun actualizarNumero(numero: Int) {
        Log.d(TAG_LOG, "actualizamos numero en Datos")
        Datos.numero = numero
    }
}