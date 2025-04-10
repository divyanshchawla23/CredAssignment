package com.example.testing123

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun CircularSlider(
    initialAmount: Int = 150000,  // Initial credit amount
    maxAmount: Int = 500000,      // Max credit amount for the slider
    monthlyRate: Float = 1.04f    // Monthly interest rate
) {
    var sliderValue by remember { mutableStateOf(0.3f) }  // Initial position
    val sweepAngle = sliderValue * 360f                   // Angle for slider progress
    val currentAmount = (sliderValue * maxAmount).roundToInt()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(250.dp)  // Size of the entire circular slider
            .pointerInput(Unit) {                         // Detect drag gestures for slider adjustment
                detectDragGestures { change, _ ->
                    val angle = calculateAngle(change.position.x, change.position.y, size.width, size.height)
                    sliderValue = (angle / 360f).coerceIn(0f, 1f)  // Update slider value based on drag
                }
            }
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            // Background circle (full arc)
            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 20f, cap = StrokeCap.Round)
            )

            // Foreground progress arc
            drawArc(
                color = Color(0xFFE68673),
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 20f, cap = StrokeCap.Round)
            )

            // Knob position calculation based on the current angle
            val angleInRadians = Math.toRadians((sweepAngle - 90).toDouble())
            val radius = size.minDimension / 2
            val knobX = center.x + radius * cos(angleInRadians).toFloat()
            val knobY = center.y + radius * sin(angleInRadians).toFloat()

            // Draw the knob at the end of the arc
            drawCircle(
                color = Color(0xFFE68673),
                radius = 15f,
                center = Offset(knobX, knobY)
            )
        }

        // Text content inside the slider
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "credit amount", style = MaterialTheme.typography.bodyMedium, color = Color(150,150,148))
            Text(
                text = "₹${currentAmount}",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
            Text(
                text = "@${monthlyRate}% monthly",
                color = Color(0xFF7CB342),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

// Helper function to calculate angle based on touch position
fun calculateAngle(x: Float, y: Float, width: Int, height: Int): Float {
    val dx = x - width / 2
    val dy = y - height / 2
    val angle = atan2(dy, dx) * (180 / Math.PI).toFloat() + 180
    return angle
}