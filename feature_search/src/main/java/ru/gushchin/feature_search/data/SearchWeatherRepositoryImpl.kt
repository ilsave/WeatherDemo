package ru.gushchin.feature_search.data

import ru.gushchin.feature_search.domain.Resource
import ru.gushchin.feature_search.presentation.SillySearchCity

class SearchWeatherRepositoryImpl: SearchWeatherRepository {
    override suspend fun getCityWeather(name: String): Resource<SillySearchCity> {
        return Resource.Error(message = "hello there!")
    }
}