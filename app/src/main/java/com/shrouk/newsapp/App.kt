package com.shrouk.newsapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :Application() {
    override fun onCreate() {

        appContext= this.applicationContext
        super.onCreate()

    }
    companion object {
        lateinit var appContext: Context
        var instance: App? = App()
//        fun getResources(): Resources? {
//            return instance!!.resources
//        }
//        fun currentActivity(): Activity {
//            return currentActivity()
//        }

        fun applicationContext(): Context {
            return appContext
        }
    }
}