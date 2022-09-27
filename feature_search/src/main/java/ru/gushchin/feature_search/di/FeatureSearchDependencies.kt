package ru.gushchin.feature_search.di

import ru.gushchin.core_network.data.api.WeatherApi

interface FeatureSearchDependencies {
    fun weatherApi(): WeatherApi
}