package ru.gushchin.weatherapp.di

import android.content.Context
import ru.gushchin.core_local_storage.di.DaggerLocalDatabaseApiComponent
import ru.gushchin.core_network.di.DaggerWeatherApiComponent
import ru.gushchin.feature_detail.di.DaggerFeatureDetailComponent_FeatureDetailDependenciesComponent
import ru.gushchin.feature_detail.di.FeatureDetailComponent
import ru.gushchin.weatherapp.WeatherApplication

object FeatureInjectorProxy {
    fun initFeatureDetailDI(context: Context) {
        FeatureDetailComponent.initAndGet(
            DaggerFeatureDetailComponent_FeatureDetailDependenciesComponent.builder()
                .localStorageApi(DaggerLocalDatabaseApiComponent.builder().build())
                .networkApi(DaggerWeatherApiComponent.builder().build())
                .build()
        , context)
    }
}