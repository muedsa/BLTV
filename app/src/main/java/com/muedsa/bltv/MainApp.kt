package com.muedsa.bltv

import android.app.Application
import timber.log.Timber

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (EnvConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}