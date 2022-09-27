package ru.gushchin.feature_search.data

import ru.gushchin.core_network.data.api.WeatherApi
import ru.gushchin.feature_search.domain.Resource
import ru.gushchin.feature_search.presentation.SillySearchCity
import javax.inject.Inject

class SearchWeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
): SearchWeatherRepository {

    override suspend fun getCityWeather(name: String): Resource<SillySearchCity> {
        return Resource.Success(data = SillySearchCity(name = "bal", "321"))
    }
}