package ru.gushchin.feature_detail.domain

import ru.gushchin.feature_detail.data.WeatherRepository
import ru.gushchin.feature_detail.data.WeatherRepositoryImpl
import ru.gushchin.feature_detail.domain.model.Weather

class CurrentCityWeatherInteractor: CurrentCityWeatherUseCases {

    val repo: WeatherRepository = WeatherRepositoryImpl()

    override suspend fun getCurrentCityWeatherBy(page: Int): List<Weather> {
        repo.getCurrentCityWeather()
        return emptyList()
    }
}