package com.bruce.tt.utilities.logging

import android.util.Log
import com.bruce.tt.utilities.BuildConfig


object AppLogger {
    private const val TAG = "AppLogger"

    fun d(tag: String = TAG, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }

    fun e(tag: String = TAG, message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }

}