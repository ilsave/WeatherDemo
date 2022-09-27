package ru.gushchin.feature_detail.domain

import ru.gushchin.feature_detail.data.models.City
import ru.gushchin.feature_detail.data.models.Weather


interface CurrentCityWeatherUseCases {
    suspend fun getCurrentCityWeatherBy(city: City) : Resource<Weather>
}