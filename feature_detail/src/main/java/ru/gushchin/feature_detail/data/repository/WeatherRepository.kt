package ru.gushchin.feature_detail.data.repository

import ru.gushchin.feature_detail.data.models.City
import ru.gushchin.feature_detail.data.models.Weather
import ru.gushchin.feature_detail.domain.Resource

interface WeatherRepository {
    suspend fun getCurrentCityWeather(city: City): Resource<Weather>
}