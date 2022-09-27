package ru.gushchin.feature_detail.domain

import ru.gushchin.feature_detail.data.models.Weather

interface CurrentCityWeatherUseCases {
    suspend fun getCurrentCityWeatherBy(lat: Double, lon: Double) : Resource<Weather>
}