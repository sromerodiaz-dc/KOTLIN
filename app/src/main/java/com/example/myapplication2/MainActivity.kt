package com.example.myapplication2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inicializamos ViewModel
        val miViewModel = MyViewModel()

        enableEdgeToEdge()
        setContent {
            // llamamos a la IU pasando el ViewModel
            GameScreen(miViewModel)
        }
    }
}