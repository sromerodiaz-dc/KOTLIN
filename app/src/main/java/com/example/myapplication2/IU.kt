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

    if (!Datos.haStarted.value) {
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
                    miViewModel.onStart()
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
}


@Composable
fun FourColoredQuadrants(miViewModel: MyViewModel) {
    val TAG_LOG = "miDebug2" // Etiqueta para el log de depuración
    Box(
        modifier = Modifier.fillMaxSize(), // El Box ocupa todo el tamaño disponible
        contentAlignment = Alignment.Center // Centra el contenido dentro del Box
    ) {
        if (Datos.haStarted.value) { // Verifica si el juego ha comenzado
            // Usamos un Box para superponer áreas clicables sobre los arcos
            Box(modifier = Modifier.size(200.dp)) {
                // Dibujamos cada cuadrante con un color diferente
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val canvasWidth = size.width // Ancho del canvas
                    val canvasHeight = size.height // Alto del canvas

                    // Dibuja el cuadrante rojo
                    drawArc(
                        color = Color.Red,
                        startAngle = 0f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = Size(canvasWidth, canvasHeight),
                        style = Fill
                    )
                    // Dibuja el cuadrante verde
                    drawArc(
                        color = Color.Green,
                        startAngle = 90f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = Size(canvasWidth, canvasHeight),
                        style = Fill
                    )
                    // Dibuja el cuadrante azul
                    drawArc(
                        color = Color.Blue,
                        startAngle = 180f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = Size(canvasWidth, canvasHeight),
                        style = Fill
                    )
                    // Dibuja el cuadrante amarillo
                    drawArc(
                        color = Color.Yellow,
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = Size(canvasWidth, canvasHeight),
                        style = Fill
                    )
                }

                // Definimos áreas clicables para cada cuadrante
                val quadrantSize = 200.dp / 2 // Tamaño de cada cuadrante

                // Cuadrante rojo
                Box(
                    modifier = Modifier
                        .size(quadrantSize)
                        .align(Alignment.TopStart) // Alinea en la parte superior izquierda
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                Log.d(TAG_LOG, "Dentro del onClick") // Log de clic
                                miViewModel.compararRandom(0) // Llama a la función para comparar con el cuadrante rojo
                            })
                        }
                )

                // Cuadrante verde
                Box(
                    modifier = Modifier
                        .size(quadrantSize)
                        .align(Alignment.TopEnd) // Alinea en la parte superior derecha
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                miViewModel.compararRandom(1) // Llama a la función para comparar con el cuadrante verde
                            })
                        }
                )

                // Cuadrante azul
                Box(
                    modifier = Modifier
                        .size(quadrantSize)
                        .align(Alignment.BottomStart) // Alinea en la parte inferior izquierda
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                miViewModel.compararRandom(2) // Llama a la función para comparar con el cuadrante azul
                            })
                        }
                )

                // Cuadrante amarillo
                Box(
                    modifier = Modifier
                        .size(quadrantSize)
                        .align(Alignment.BottomEnd) // Alinea en la parte inferior derecha
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                miViewModel.compararRandom(3) // Llama a la función para comparar con el cuadrante amarillo
                            })
                        }
                )
            }
        } else {
            // Si el juego no ha comenzado, dibujamos los cuadrantes con opacidad reducida
            Box(modifier = Modifier.size(200.dp)) {
                // Dibujamos cada cuadrante con un color diferente y opacidad
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val canvasWidth = size.width // Ancho del canvas
                    val canvasHeight = size.height // Alto del canvas

                    // Dibuja el cuadrante rojo con opacidad
                    drawArc(
                        color = Color.Red.copy(alpha = 0.3f),  // Rojo con opacidad reducida
                        startAngle = 0f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = Size(canvasWidth, canvasHeight),
                        style = Fill
                    )
                    // Dibuja el cuadrante verde con opacidad
                    drawArc(
                        color = Color.Green.copy(alpha =  0.3f),  // Verde con opacidad reducida
                        startAngle = 90f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = Size(canvasWidth, canvasHeight),
                        style = Fill
                    )
                    // Dibuja el cuadrante azul con opacidad
                    drawArc(
                        color = Color.Blue.copy(alpha = 0.3f),  // Azul con opacidad reducida
                        startAngle = 180f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = Size(canvasWidth, canvasHeight),
                        style = Fill
                    )
                    // Dibuja el cuadrante amarillo con opacidad
                    drawArc(
                        color = Color.Yellow.copy(alpha = 0.3f),  // Amarillo con opacidad reducida
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = Size(canvasWidth, canvasHeight),
                        style = Fill
                    )
                }
            }
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