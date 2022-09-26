package ru.gushchin.feature_detail.domain

import ru.gushchin.core_network.data.models.WeatherDTO
import ru.gushchin.feature_detail.data.WeatherRepository
import javax.inject.Inject

class CurrentCityWeatherInteractor @Inject constructor(
    private val repo: WeatherRepository
): CurrentCityWeatherUseCases {

    override suspend fun getCurrentCityWeatherBy(lat: Double, lon: Double): Resource<WeatherDTO> {
        return repo.getCurrentCityWeather(lat, lon)
    }
}