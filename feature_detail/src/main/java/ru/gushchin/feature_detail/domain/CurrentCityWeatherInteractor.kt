package ru.gushchin.feature_detail.domain

import ru.gushchin.core_network.model.WeatherDTO
import ru.gushchin.feature_detail.data.WeatherRepository
import ru.gushchin.feature_detail.data.WeatherRepositoryImpl
import javax.inject.Inject

class CurrentCityWeatherInteractor @Inject constructor(
    private val repo: WeatherRepository
): CurrentCityWeatherUseCases {

    override suspend fun getCurrentCityWeatherBy(lat: Double, lon: Double): Resource<WeatherDTO> {
        return repo.getCurrentCityWeather(lat, lon)
    }
}