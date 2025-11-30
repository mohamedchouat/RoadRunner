package com.chtmed.roadrunner.di

import android.app.Application
import com.chtmed.roadrunner.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RoadRunnerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RoadRunnerApp)
            modules(appModule)
        }
    }
}
