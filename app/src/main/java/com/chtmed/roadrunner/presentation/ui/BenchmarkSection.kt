package com.chtmed.roadrunner.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
private const val REFERENCE_PI = "3.1415926535"

@Composable
fun ColoredPiResult(piResult: Double?) {
    if (piResult == null) return
    val piString = String.format("%.10f", piResult)

    var mismatchFound = false
    Row {
        piString.forEachIndexed { index, c ->
            val refChar = REFERENCE_PI.getOrNull(index)
            val bgColor = if (!mismatchFound && c == refChar) {
                Color(0xFFB2FF59)
            } else {
                mismatchFound = true
                Color(0xFFFF5252)
            }
            Text(
                text = c.toString(),
                modifier = Modifier
                    .background(bgColor)
                    .padding(horizontal = 2.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun BenchmarkSection(
    title: String,
    piResult: Double?,
    result: Long?,
    points: List<Long>,
    loading: Boolean,
    color: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(8.dp))

        if (loading) {
            CircularProgressIndicator()
        } else {
            Button(onClick = onClick) {
                Text("Start Test")
            }
        }

        result?.let {
            Spacer(Modifier.height(8.dp))
            Text("Execution Time: $it ms")
        }

        piResult?.let {
            Spacer(Modifier.height(8.dp))
            Text("Final π ≈")
            ColoredPiResult(it)
        }

        if (points.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                val max = points.maxOrNull()?.toFloat() ?: 1f
                val step = size.width / points.size
                var last: Offset? = null
                points.forEachIndexed { index, value ->
                    val x = index * step
                    val y = size.height - (value.toFloat() / max * size.height)
                    val current = Offset(x, y)
                    if (last != null) {
                        drawLine(color, last!!, current, strokeWidth = 3f)
                    }
                    last = current
                }
            }
        }
    }
}
