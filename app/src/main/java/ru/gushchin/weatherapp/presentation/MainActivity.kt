package ru.gushchin.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gushchin.core_local_storage.di.LocalStorageInjectorProxy
import ru.gushchin.core_network.di.NetworkInjectorProxy
import ru.gushchin.weatherapp.R
import ru.gushchin.weatherapp.WeatherApplication
import ru.gushchin.weatherapp.di.FeatureInjectorProxy

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocalStorageInjectorProxy.initContext(applicationContext)
        NetworkInjectorProxy.initWorkManager(WeatherApplication.workManager)
        FeatureInjectorProxy.initFeatureDetailDI(applicationContext)
        FeatureInjectorProxy.initFeatureFavoriteDI()
        FeatureInjectorProxy.initFeatureSearchDI()
        setContentView(R.layout.activity_main)
    }
}