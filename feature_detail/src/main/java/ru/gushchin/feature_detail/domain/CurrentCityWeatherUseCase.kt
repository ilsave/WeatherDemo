package ru.gushchin.feature_detail.domain

import ru.gushchin.core_network.model.WeatherDTO

interface CurrentCityWeatherUseCases {
    suspend fun getCurrentCityWeatherBy(lat: Double, lon: Double) : Resource<WeatherDTO>
}