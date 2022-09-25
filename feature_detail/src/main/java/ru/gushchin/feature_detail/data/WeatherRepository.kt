package ru.gushchin.feature_detail.data

import ru.gushchin.core_network.model.WeatherDTO
import ru.gushchin.feature_detail.domain.Resource

interface WeatherRepository {
    suspend fun getCurrentCityWeather(): Resource<WeatherDTO>
}