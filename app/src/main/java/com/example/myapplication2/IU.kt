package com.example.myapplication2

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.pointerInput

/**
 * Interfaz de usuario
 * Modificado desde Code
 */

@Composable
fun IU(miViewModel: MyViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Boton(miViewModel, Colores.CLASE_AZUL)
        FourColoredQuadrants(miViewModel)
    }
}

@Composable
fun Boton(miViewModel: MyViewModel, enum_color: Colores) {

    // para que sea mas facil la etiqueta del log
    val TAG_LOG = "miDebug"

    // Box to center the button
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter // Align items to the top center
    ) {
        // Offset the button 150 pixels down
        Button(
            // utilizamos el color del enum
            colors = ButtonDefaults.buttonColors(enum_color.color),
            onClick = {
                Log.d(TAG_LOG, "Dentro del onClick")
                miViewModel.crearRandom()
            },
            modifier = Modifier
                .size(80.dp, 40.dp)
                .offset(y = 150.dp) // Move the button 150 pixels down
        ) {
            // utilizamos el texto del enum
            Text("START", fontSize = 10.sp)
        }
    }
}


@Composable
fun FourColoredQuadrants(miViewModel: MyViewModel) {
    val TAG_LOG = "miDebug2"
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Using a Box to layer clickable areas over the arcs
        Box(modifier = Modifier.size(200.dp)) {
            // Draw each quadrant with a different color
            Canvas(modifier = Modifier.fillMaxSize()) {
                val canvasWidth = size.width
                val canvasHeight = size.height

                drawArc(
                    color = Color.Red,
                    startAngle = 0f,
                    sweepAngle = 90f,
                    useCenter = true,
                    size = Size(canvasWidth, canvasHeight),
                    style = Fill
                )
                drawArc(
                    color = Color.Green,
                    startAngle = 90f,
                    sweepAngle = 90f,
                    useCenter = true,
                    size = Size(canvasWidth, canvasHeight),
                    style = Fill
                )
                drawArc(
                    color = Color.Blue,
                    startAngle = 180f,
                    sweepAngle = 90f,
                    useCenter = true,
                    size = Size(canvasWidth, canvasHeight),
                    style = Fill
                )
                drawArc(
                    color = Color.Yellow,
                    startAngle = 270f,
                    sweepAngle = 90f,
                    useCenter = true,
                    size = Size(canvasWidth, canvasHeight),
                    style = Fill
                )
            }

            // Define clickable areas for each quadrant
            val quadrantSize = 200.dp / 2

            // rojo
            Box(
                modifier = Modifier
                    .size(quadrantSize)
                    .align(Alignment.TopStart)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            Log.d(TAG_LOG, "Dentro del onClick")
                            miViewModel.compararRandom(0)
                        })
                    }
            )

            // verde
            Box(
                modifier = Modifier
                    .size(quadrantSize)
                    .align(Alignment.TopEnd)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            miViewModel.compararRandom(1)
                        })
                    }
            )

            // azul
            Box(
                modifier = Modifier
                    .size(quadrantSize)
                    .align(Alignment.BottomStart)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            miViewModel.compararRandom(2)
                        })
                    }
            )

            // amarillo
            Box(
                modifier = Modifier
                    .size(quadrantSize)
                    .align(Alignment.BottomEnd)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            miViewModel.compararRandom(3)
                        })
                    }
            )
        }
    }
}

@Composable
fun GameScreen(viewModel: MyViewModel) {
    // Observa el estado hasWon
    val hasWon = Datos.hasWon
    val TAG_LOG = "miDebug"

    // Usar un Box para centrar el contenido
    Box(
        modifier = Modifier.fillMaxSize(), // Ocupa todo el tamaño de la pantalla
        contentAlignment = Alignment.Center // Centra el contenido en el Box
    ) {
        if (!hasWon.value) {
            IU(miViewModel = viewModel)
            Log.d(TAG_LOG, "empezando...")
        } else {
            Text(text = "¡Has ganado!", fontSize = 24.sp)
            // Offset the button 150 pixels down
            Button(
                // utilizamos el color del enum
                colors = ButtonDefaults.buttonColors(Color.Green),
                onClick = {
                    Log.d(TAG_LOG, "Dentro del onClick $hasWon")
                    hasWon.value = !hasWon.value
                },
                modifier = Modifier
                    .size(80.dp, 40.dp)
                    .offset(y = 150.dp) // Move the button 150 pixels down
            ) {
                // utilizamos el texto del enum
                Text("Volver a empezar", fontSize = 10.sp)
            }
        }
    }
}

/**
 * Preview de la interfaz de usuario
 */
@Preview(showBackground = true)
@Composable
fun IUPreview() {
    val viewModel = MyViewModel()
    GameScreen(viewModel)
}