package com.chtmed.roadrunner.di

import com.chtmed.roadrunner.data.BenchmarkRunner
import com.chtmed.roadrunner.domain.PiCalculator
import com.chtmed.roadrunner.presentation.viewmodel.BenchmarkViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { PiCalculator() }
    single { BenchmarkRunner(get()) }
    viewModel { BenchmarkViewModel(get()) }
}
