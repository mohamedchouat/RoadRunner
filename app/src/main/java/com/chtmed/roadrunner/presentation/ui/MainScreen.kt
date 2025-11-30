package com.chtmed.roadrunner.presentation.ui

import android.graphics.fonts.FontStyle
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chtmed.roadrunner.data.BenchmarkConfig
import com.chtmed.roadrunner.presentation.viewmodel.BenchmarkViewModel
import com.chtmed.roadrunner.presentation.ui.theme.BenchmarkColors
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(viewModel: BenchmarkViewModel = getViewModel()) {
    val state by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val ranking = remember { mutableStateListOf<String>() }
    var expanded by remember { mutableStateOf(false) }
    var selectedIterations by remember { mutableStateOf(BenchmarkConfig.iterations) }

    LaunchedEffect(state.coroutineFinished) {
        if (state.coroutineFinished && state.coroutineResult != null) {
            val speed = BenchmarkConfig.iterations.toDouble() / state.coroutineResult!!.toDouble()
            ranking.add(
                "Coroutine → Iterations: $selectedIterations, Speed: ${String.format("%.2f", speed)} it/ms, Time: ${state.coroutineResult} ms"
            )
        }
    }

    LaunchedEffect(state.rxJavaFinished) {
        if (state.rxJavaFinished && state.rxJavaResult != null) {
            val speed = BenchmarkConfig.iterations.toDouble() / state.rxJavaResult!!.toDouble()
            ranking.add(
                "RxJava → Iterations: $selectedIterations, Speed: ${String.format("%.2f", speed)} it/ms, Time: ${state.rxJavaResult} ms"
            )
        }
    }

    LaunchedEffect(state.rxKotlinFinished) {
        if (state.rxKotlinFinished && state.rxKotlinResult != null) {
            val speed = BenchmarkConfig.iterations.toDouble() / state.rxKotlinResult!!.toDouble()
            ranking.add(
                "RxKotlin → Iterations: $selectedIterations, Speed: ${String.format("%.2f", speed)} it/ms, Time: ${state.rxKotlinResult} ms"
            )
        }
    }

    LaunchedEffect(state.threadPoolFinished) {
        if (state.threadPoolFinished && state.threadPoolResult != null) {
            val speed = BenchmarkConfig.iterations.toDouble() / state.threadPoolResult!!.toDouble()
            ranking.add(
                "ThreadPool → Iterations: $selectedIterations, Speed: ${String.format("%.2f", speed)} it/ms, Time: ${state.threadPoolResult} ms"
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text("Road Runner Benchmark Engine", style = MaterialTheme.typography.titleLarge)

        Text(
            "Road Runner Benchmark Engine\n\n" +
                    "This application compares CPU-intensive processing performance using 4 different technologies: " +
                    "Kotlin Coroutines, RxJava, RxKotlin and ThreadPool. Each test emits timing samples and draws a real-time graph until completion. " +
                    "When finished, the final π value, execution time, and race speed are displayed.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))

        Text("You can change iterations:", style = MaterialTheme.typography.bodyMedium)

        Box {
            Button(onClick = { expanded = true }) {
                Text("Iterations: $selectedIterations")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOf(1_000_000, 10_000_000, 100_000_000, 1_000_000_000).forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.toString()) },
                        onClick = {
                            selectedIterations = option
                            BenchmarkConfig.iterations = option
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                Log.d("BenchmarkUI", "Run All Benchmarks clicked with $selectedIterations iterations")
                ranking.clear()
                viewModel.runAllBenchmarks()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Run All Benchmarks")
        }

        Spacer(Modifier.height(16.dp))

        CombinedResultsGraph(
            coroutinePoints = state.coroutinePoints,
            rxJavaPoints = state.rxJavaPoints,
            rxKotlinPoints = state.rxKotlinPoints,
            threadPoolPoints = state.threadPoolPoints
        )

        Spacer(Modifier.height(24.dp))

        BenchmarkSection(
            title = "Coroutine Benchmark",
            piResult = state.coroutinePi,
            result = state.coroutineResult,
            points = state.coroutinePoints,
            loading = !state.coroutineFinished,
            color = BenchmarkColors.coroutine
        ) { viewModel.runCoroutineTest() }

        BenchmarkSection(
            title = "RxJava Benchmark",
            piResult = state.rxJavaPi,
            result = state.rxJavaResult,
            points = state.rxJavaPoints,
            loading = !state.rxJavaFinished,
            color = BenchmarkColors.rxJava
        ) { viewModel.runRxJavaTest() }

        BenchmarkSection(
            title = "RxKotlin Benchmark",
            piResult = state.rxKotlinPi,
            result = state.rxKotlinResult,
            points = state.rxKotlinPoints,
            loading = !state.rxKotlinFinished,
            color = BenchmarkColors.rxKotlin
        ) { viewModel.runRxKotlinTest() }

        BenchmarkSection(
            title = "ThreadPool Benchmark",
            piResult = state.threadPoolPi,
            result = state.threadPoolResult,
            points = state.threadPoolPoints,
            loading = !state.threadPoolFinished,
            color = BenchmarkColors.threadPool
        ) { viewModel.runThreadPoolTest() }

        Spacer(Modifier.height(32.dp))

        if (ranking.isNotEmpty()) {
            Text("🏁 Benchmark Ranking:", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
            ranking.forEachIndexed { index, entry ->
                val techName = entry.substringBefore("→").trim()
                val details = "→ " + entry.substringAfter("→").trim()

                Row {
                    Text(
                        text = "${index + 1}",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = techName,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = details,
                        style = MaterialTheme.typography.bodyMedium )
                }
                Spacer(Modifier.height(6.dp))
            }
        }

    }
}
