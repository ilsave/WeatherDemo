package ru.gushchin.feature_detail.domain

import ru.gushchin.feature_detail.domain.model.Weather

interface CurrentCityWeatherUseCases {
    suspend fun getCurrentCityWeatherBy(page: Int) : List<Weather>
}