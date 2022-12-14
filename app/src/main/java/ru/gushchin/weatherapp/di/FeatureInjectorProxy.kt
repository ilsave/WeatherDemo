package ru.gushchin.weatherapp.di

import android.content.Context
import ru.gushchin.core_local_storage.di.DaggerLocalDatabaseApiComponent
import ru.gushchin.core_network.di.DaggerWeatherApiComponent
import ru.gushchin.feature_detail.di.DaggerFeatureDetailComponent_FeatureDetailDependenciesComponent
import ru.gushchin.feature_detail.di.FeatureDetailComponent
import ru.gushchin.feature_favorite.di.DaggerFeatureFavoriteComponent_FeatureFavoriteDependenciesComponent
import ru.gushchin.feature_favorite.di.FeatureFavoriteComponent
import ru.gushchin.feature_search.di.DaggerFeatureSearchComponent_FeatureSearchDependenciesComponent
import ru.gushchin.feature_search.di.FeatureSearchComponent

object FeatureInjectorProxy {
    fun initFeatureDetailDI(context: Context) {
        FeatureDetailComponent.initAndGet(
            DaggerFeatureDetailComponent_FeatureDetailDependenciesComponent.builder()
                .localStorageApi(DaggerLocalDatabaseApiComponent.builder().build())
                .networkApi(DaggerWeatherApiComponent.builder().build())
                .build(), context
        )
    }

    fun initFeatureFavoriteDI() {
        FeatureFavoriteComponent.initAndGet(
            DaggerFeatureFavoriteComponent_FeatureFavoriteDependenciesComponent.builder()
                .localStorageApi(DaggerLocalDatabaseApiComponent.builder().build())
                .build()
        )
    }

    fun initFeatureSearchDI() {
        FeatureSearchComponent.initAndGet(
            DaggerFeatureSearchComponent_FeatureSearchDependenciesComponent.builder()
                .networkApi(DaggerWeatherApiComponent.builder().build())
                .build()
        )
    }

}