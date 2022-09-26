package ru.gushchin.weatherapp.di

import ru.gushchin.core_local_storage.di.DaggerLocalDatabaseApiComponent
import ru.gushchin.feature_detail.di.DaggerFeatureDetailComponent_FeatureDetailDependenciesComponent
import ru.gushchin.feature_detail.di.FeatureDetailComponent

object FeatureInjectorProxy {
    fun initFeatureDetailDI() {
        FeatureDetailComponent.initAndGet(
            DaggerFeatureDetailComponent_FeatureDetailDependenciesComponent.builder()
                .localStorageApi(DaggerLocalDatabaseApiComponent.builder().build())
                .build()
        )
    }
}