package ru.gushchin.feature_search.data

import ru.gushchin.feature_search.domain.Resource
import ru.gushchin.feature_search.presentation.SillySearchCity

interface SearchWeatherRepository {
    suspend fun getCityWeather(name: String): Resource<SillySearchCity>
}