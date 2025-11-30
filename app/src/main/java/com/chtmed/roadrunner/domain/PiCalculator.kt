package com.chtmed.roadrunner.domain

class PiCalculator {

    fun calculate(iterations: Long = 10_000_000): Double {
        var pi = 0.0
        var sign = 1.0
        for (i in 0 until iterations) {
            pi += sign / (2.0 * i + 1)
            sign = -sign
        }
        return pi * 4
    }
}
