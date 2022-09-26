package ru.gushchin.weatherapp

import android.app.Application
import android.content.Context

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @Volatile
        lateinit var context: Context
        private set
    }
}