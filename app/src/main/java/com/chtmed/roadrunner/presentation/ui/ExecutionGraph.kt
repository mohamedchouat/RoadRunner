package com.chtmed.roadrunner.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExecutionGraph(points: List<Long>) {
    if (points.isEmpty()) return

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        val max = points.maxOrNull()?.toFloat() ?: 1f
        val step = size.width / points.size

        points.forEachIndexed { index, value ->
            val x = index * step
            val y = size.height - (value.toFloat() / max * size.height)
            drawRect(
                color = Color(0xFF4CAF50),
                topLeft = Offset(x, y),
                size = androidx.compose.ui.geometry.Size(step * 0.8f, size.height - y)
            )
        }
    }
}
