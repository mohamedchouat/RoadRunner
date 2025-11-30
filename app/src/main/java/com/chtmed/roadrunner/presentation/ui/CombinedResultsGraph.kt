package com.chtmed.roadrunner.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chtmed.roadrunner.presentation.ui.theme.BenchmarkColors

@Composable
fun CombinedResultsGraph(
    coroutinePoints: List<Long>,
    rxJavaPoints: List<Long>,
    rxKotlinPoints: List<Long>,
    threadPoolPoints: List<Long>,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val max = listOf(
            coroutinePoints.maxOrNull() ?: 1L,
            rxJavaPoints.maxOrNull() ?: 1L,
            rxKotlinPoints.maxOrNull() ?: 1L,
            threadPoolPoints.maxOrNull() ?: 1L
        ).max().toFloat()

        fun drawLine(points: List<Long>, color: Color) {
            if (points.size < 2) return
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

        drawLine(coroutinePoints, BenchmarkColors.coroutine)
        drawLine(rxJavaPoints, BenchmarkColors.rxJava)
        drawLine(rxKotlinPoints, BenchmarkColors.rxKotlin)
        drawLine(threadPoolPoints, BenchmarkColors.threadPool)
    }
}
