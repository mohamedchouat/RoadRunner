package com.chtmed.roadrunner.data

import kotlinx.coroutines.*
import kotlin.math.pow

class BenchmarkRunner(private val dispatcher: CoroutineDispatcher = Dispatchers.Default) {

    fun runCoroutine(onUpdate: (List<Long>, Double?, Long?, Boolean) -> Unit) {
        runGeneric(onUpdate, "Coroutine")
    }

    fun runRxJava(onUpdate: (List<Long>, Double?, Long?, Boolean) -> Unit) {
        runGeneric(onUpdate, "RxJava")
    }

    fun runRxKotlin(onUpdate: (List<Long>, Double?, Long?, Boolean) -> Unit) {
        runGeneric(onUpdate, "RxKotlin")
    }

    fun runThreadPool(onUpdate: (List<Long>, Double?, Long?, Boolean) -> Unit) {
        runGeneric(onUpdate, "ThreadPool")
    }

    private fun runGeneric(
        onUpdate: (List<Long>, Double?, Long?, Boolean) -> Unit,
        label: String
    ) {
        val points = mutableListOf<Long>()
        val start = System.currentTimeMillis()

        CoroutineScope(dispatcher).launch {
            var pi = 0.0
            for (i in 0 until BenchmarkConfig.iterations) {
                pi += (-1.0).pow(i.toDouble()) / (2 * i + 1)

                if (i % 1000 == 0) {
                    val elapsed = System.currentTimeMillis() - start
                    points.add(elapsed)
                    onUpdate(points.toList(), pi * 4, null, false)
                }
            }

            val totalTime = System.currentTimeMillis() - start
            onUpdate(points.toList(), pi * 4, totalTime, true)
        }
    }
}
