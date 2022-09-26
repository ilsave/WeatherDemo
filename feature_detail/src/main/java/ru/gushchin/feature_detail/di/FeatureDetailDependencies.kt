package ru.gushchin.feature_detail.di

import ru.gushchin.core_local_storage.data.api.LocalDatabaseApi
import ru.gushchin.core_network.data.api.WeatherApi

interface FeatureDetailDependencies {
    fun localDatabaseApi(): LocalDatabaseApi
    fun weatherApi(): WeatherApi
}