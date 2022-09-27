package ru.gushchin.feature_search.domain

import ru.gushchin.feature_search.presentation.SillySearchCity

// ДОЛЖЕН ЛЕЖАТЬ В ОБЩЕМ МОДУЛЕ BASE
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}

interface SearchCityWeatherUseCase {
    suspend fun getSearchCityWeather(name: String) : Resource<SillySearchCity>
}