package com.bruce.tt.practisingapp

import android.app.Application
import android.util.Log
import com.bruce.tt.utilities.logging.AppLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PractisingApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppLogger.d( message = "Application is launched" )
    }

    companion object {
        const val TAG = "PractisingApplication"
    }
}