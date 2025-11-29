package com.chtmed.roadrunner.presentation.viewmodel

data class BenchmarkState(
    val coroutineResult: Long? = null,
    val rxJavaResult: Long? = null,
    val rxKotlinResult: Long? = null,
    val threadPoolResult: Long? = null,
    val coroutineGraph: List<Long> = emptyList(),
    val rxJavaGraph: List<Long> = emptyList(),
    val rxKotlinGraph: List<Long> = emptyList(),
    val threadPoolGraph: List<Long> = emptyList()
)
