package com.hanihashemi.earthquake

import android.app.Application
import android.util.Log
import com.bumptech.glide.annotation.GlideModule
import timber.log.Timber
import timber.log.Timber.DebugTree



class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO)
                return

//            if (t != null)
//                Crashlytics.logException(t)
        }
    }
}