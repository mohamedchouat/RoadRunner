package com.chtmed.roadrunner.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BenchmarkSection(
    title: String,
    result: Long?,
    points: List<Long>,
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

        Button(onClick = onClick) {
            Text("Start Test")
        }

        result?.let {
            Spacer(Modifier.height(8.dp))
            Text("Execution Time: $it ms")
        }

        if (points.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            ExecutionGraph(points)
        }
    }
}
