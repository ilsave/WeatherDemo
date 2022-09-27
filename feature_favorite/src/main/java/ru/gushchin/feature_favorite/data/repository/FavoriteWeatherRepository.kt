package ru.gushchin.feature_favorite.data.repository

import ru.gushchin.feature_favorite.domain.Resource
import ru.gushchin.feature_favorite.domain.Weather
import ru.gushchin.feature_favorite.presentation.SillyWeather

interface FavoriteWeatherRepository {
    suspend fun getFavoriteCityList(): Resource<List<SillyWeather?>>
}