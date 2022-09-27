package ru.gushchin.feature_detail.domain

import android.util.Log
import ru.gushchin.feature_detail.data.models.City
import ru.gushchin.feature_detail.data.models.Weather
import ru.gushchin.feature_detail.data.repository.WeatherRepository
import javax.inject.Inject

class CurrentCityWeatherInteractor @Inject constructor(
    private val repo: WeatherRepository
): CurrentCityWeatherUseCases {

    override suspend fun getCurrentCityWeatherBy(city: City): Resource<Weather> {
        return repo.getCurrentCityWeather(city)
    }
}