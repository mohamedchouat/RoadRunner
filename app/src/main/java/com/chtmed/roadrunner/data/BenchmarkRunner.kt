package com.chtmed.roadrunner.data

import com.chtmed.roadrunner.domain.PiCalculator
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class BenchmarkRunner(private val calculator: PiCalculator) {

    private val ITERATIONS = 10_000_000

    // Coroutine Runner
    suspend fun runCoroutine(): Pair<Long, List<Long>> {
        val points = mutableListOf<Long>()
        val start = System.currentTimeMillis()
        withContext(Dispatchers.Default) { calculator.calculate(ITERATIONS) }
            .also { points.add(System.currentTimeMillis() - start) }
        val end = System.currentTimeMillis()
        return end - start to points
    }

    // RxJava Runner
    fun runRxJava(callback: (Long, List<Long>) -> Unit) {
        val points = mutableListOf<Long>()
        val start = System.currentTimeMillis()
        Single.fromCallable { calculator.calculate(ITERATIONS) }
            .subscribeOn(Schedulers.computation())
            .subscribe {
                points.add(System.currentTimeMillis() - start)
                callback(System.currentTimeMillis() - start, points)
            }
    }

    // RxKotlin Runner
    fun runRxKotlin(callback: (Long, List<Long>) -> Unit) {
        val points = mutableListOf<Long>()
        val start = System.currentTimeMillis()
        Single.just(ITERATIONS)
            .map { calculator.calculate(it) }
            .subscribeOn(Schedulers.computation())
            .subscribe {
                points.add(System.currentTimeMillis() - start)
                callback(System.currentTimeMillis() - start, points)
            }
    }

    // ThreadPool Runner
    fun runThreadPool(callback: (Long, List<Long>) -> Unit) {
        val points = mutableListOf<Long>()
        val start = System.currentTimeMillis()
        val executor = Executors.newFixedThreadPool(4)
        executor.execute {
            calculator.calculate(ITERATIONS)
            points.add(System.currentTimeMillis() - start)
            callback(System.currentTimeMillis() - start, points)
        }
    }
}
