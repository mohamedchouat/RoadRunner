package com.chtmed.roadrunner.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chtmed.roadrunner.data.BenchmarkRunner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BenchmarkViewModel(private val runner: BenchmarkRunner) : ViewModel() {

    private val _uiState = MutableStateFlow(BenchmarkState())
    val uiState = _uiState.asStateFlow()

    fun runCoroutineTest() = viewModelScope.launch {
        val (time, points) = runner.runCoroutine()
        _uiState.update { it.copy(coroutineResult = time, coroutineGraph = points) }
    }

    fun runRxJavaTest() {
        runner.runRxJava { time, points ->
            _uiState.update { it.copy(rxJavaResult = time, rxJavaGraph = points) }
        }
    }

    fun runRxKotlinTest() {
        runner.runRxKotlin { time, points ->
            _uiState.update { it.copy(rxKotlinResult = time, rxKotlinGraph = points) }
        }
    }

    fun runThreadPoolTest() {
        runner.runThreadPool { time, points ->
            _uiState.update { it.copy(threadPoolResult = time, threadPoolGraph = points) }
        }
    }
}
