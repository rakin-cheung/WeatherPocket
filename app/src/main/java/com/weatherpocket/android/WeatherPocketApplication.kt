package com.weatherpocket.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class WeatherPocketApplication:Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context

        const val TOKEN_CAIYUN = "k0goXb3tl2di9ncL"
        const val TOKEN_LBS = "1d49121a24232b3568c8a6a393521cb9"
        const val TOKEN_GOOGLE = "AIzaSyB8Zon1f_7MUltXsO9Owzfc1R3GqXBD1pk"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}