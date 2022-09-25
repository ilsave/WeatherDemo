package ru.gushchin.feature_detail.domain

import ru.gushchin.core_network.model.WeatherDTO
import ru.gushchin.feature_detail.data.WeatherRepository
import ru.gushchin.feature_detail.data.WeatherRepositoryImpl

class CurrentCityWeatherInteractor: CurrentCityWeatherUseCases {

    val repo: WeatherRepository = WeatherRepositoryImpl()

    override suspend fun getCurrentCityWeatherBy(lat: Double, lon: Double): Resource<WeatherDTO> {
        return repo.getCurrentCityWeather(lat, lon)
    }
}