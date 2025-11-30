package com.chtmed.roadrunner.presentation.viewmodel

data class BenchmarkState(
    val coroutinePoints: List<Long> = emptyList(),
    val rxJavaPoints: List<Long> = emptyList(),
    val rxKotlinPoints: List<Long> = emptyList(),
    val threadPoolPoints: List<Long> = emptyList(),
    val coroutinePi: Double? = null,
    val rxJavaPi: Double? = null,
    val rxKotlinPi: Double? = null,
    val threadPoolPi: Double? = null,
    val coroutineResult: Long? = null,
    val rxJavaResult: Long? = null,
    val rxKotlinResult: Long? = null,
    val threadPoolResult: Long? = null,
     val coroutineFinished: Boolean = true,
    val rxJavaFinished: Boolean = true,
    val rxKotlinFinished: Boolean = true,
    val threadPoolFinished: Boolean = true
)
