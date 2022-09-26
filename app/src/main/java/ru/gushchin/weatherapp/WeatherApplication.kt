package ru.gushchin.weatherapp

import android.app.Application
import android.content.Context
import androidx.work.WorkManager

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        workManager = WorkManager.getInstance(this)
    }

    companion object {
        @Volatile
        lateinit var context: Context
            private set

        @Volatile
        lateinit var workManager: WorkManager
            private set
    }
}