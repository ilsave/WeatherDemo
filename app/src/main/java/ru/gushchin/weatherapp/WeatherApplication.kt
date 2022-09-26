package ru.gushchin.weatherapp

import android.app.Application
import ru.gushchin.core_local_storage.di.LocalStorageModule
import ru.gushchin.weatherapp.di.AppComponent
import ru.gushchin.weatherapp.di.AppModule
import ru.gushchin.weatherapp.di.DaggerAppComponent

class WeatherApplication: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = this))
            .localStorageModule(LocalStorageModule(context = this))
            .build()
    }
}