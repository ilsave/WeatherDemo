package ru.gushchin.feature_detail.data

import ru.gushchin.core_network.data.models.WeatherDTO
import ru.gushchin.feature_detail.domain.Resource

interface WeatherRepository {
    suspend fun getCurrentCityWeather(lat: Double, lon: Double): Resource<WeatherDTO>
}