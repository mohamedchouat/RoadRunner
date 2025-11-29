package com.chtmed.roadrunner.presentation.ui

 import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chtmed.roadrunner.presentation.viewmodel.BenchmarkViewModel

@Composable
fun MainScreen(viewModel: BenchmarkViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            "Road Runner Benchmark Engine\n\nThis application compares CPU-intensive processing performance using 4 different technologies: Kotlin Coroutines, RxJava, RxKotlin and ThreadPool. Each test performs the same heavy mathematical calculation and displays execution time with a simple graph.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))

        BenchmarkSection(
            "Coroutine Benchmark",
            state.coroutineResult,
            state.coroutineGraph
        ) { viewModel.runCoroutineTest() }

        BenchmarkSection(
            "RxJava Benchmark",
            state.rxJavaResult,
            state.rxJavaGraph
        ) { viewModel.runRxJavaTest() }

        BenchmarkSection(
            "RxKotlin Benchmark",
            state.rxKotlinResult,
            state.rxKotlinGraph
        ) { viewModel.runRxKotlinTest() }

        BenchmarkSection(
            "ThreadPool Benchmark",
            state.threadPoolResult,
            state.threadPoolGraph
        ) { viewModel.runThreadPoolTest() }
    }
}
