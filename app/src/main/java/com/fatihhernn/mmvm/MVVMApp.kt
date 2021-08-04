package com.fatihhernn.mmvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MVVMApp:Application() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}