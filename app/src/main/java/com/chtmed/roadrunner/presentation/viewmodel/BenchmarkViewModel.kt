package com.chtmed.roadrunner.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chtmed.roadrunner.data.BenchmarkRunner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BenchmarkViewModel(private val runner: BenchmarkRunner) : ViewModel() {

    private val _uiState = MutableStateFlow(BenchmarkState())
    val uiState = _uiState.asStateFlow()

    fun runCoroutineTest() {
        runner.runCoroutine { points, pi, time, finished ->
            _uiState.update {
                it.copy(
                    coroutinePoints = points,
                    coroutinePi = pi,
                    coroutineResult = time,
                    coroutineFinished = finished
                )
            }
        }
    }

    fun runRxJavaTest() {
        runner.runRxJava { points, pi, time, finished ->
            _uiState.update {
                it.copy(
                    rxJavaPoints = points,
                    rxJavaPi = pi,
                    rxJavaResult = time,
                    rxJavaFinished = finished
                )
            }
        }
    }

    fun runRxKotlinTest() {
        runner.runRxKotlin { points, pi, time, finished ->
            _uiState.update {
                it.copy(
                    rxKotlinPoints = points,
                    rxKotlinPi = pi,
                    rxKotlinResult = time,
                    rxKotlinFinished = finished
                )
            }
        }
    }

    fun runThreadPoolTest() {
        runner.runThreadPool { points, pi, time, finished ->
            _uiState.update {
                it.copy(
                    threadPoolPoints = points,
                    threadPoolPi = pi,
                    threadPoolResult = time,
                    threadPoolFinished = finished
                )
            }
        }
    }

    fun runAllBenchmarks() {
        runCoroutineTest()
        runRxJavaTest()
        runRxKotlinTest()
        runThreadPoolTest()
    }
}
