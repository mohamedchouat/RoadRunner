package com.chtmed.roadrunner.di

import android.app.Application
import com.chtmed.roadrunner.data.BenchmarkRunner
import com.chtmed.roadrunner.domain.PiCalculator
import com.chtmed.roadrunner.presentation.viewmodel.BenchmarkViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class RoadRunnerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RoadRunnerApp)
            modules(appModule)
        }
    }
}
