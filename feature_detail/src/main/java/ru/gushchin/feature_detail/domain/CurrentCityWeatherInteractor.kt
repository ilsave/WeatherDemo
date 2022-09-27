package ru.gushchin.feature_detail.domain

import ru.gushchin.feature_detail.data.models.Weather
import ru.gushchin.feature_detail.data.repository.WeatherRepository
import javax.inject.Inject

class CurrentCityWeatherInteractor @Inject constructor(
    private val repo: WeatherRepository
): CurrentCityWeatherUseCases {

    override suspend fun getCurrentCityWeatherBy(lat: Double, lon: Double): Resource<Weather> {
        return repo.getCurrentCityWeather(lat, lon)
    }
}