package com.weatherpocket.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class WeatherPocketApplication:Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context

        const val TOKEN = "None"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}