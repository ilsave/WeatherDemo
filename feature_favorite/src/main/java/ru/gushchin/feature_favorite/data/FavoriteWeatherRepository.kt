package ru.gushchin.feature_favorite.data

import ru.gushchin.feature_favorite.domain.Resource
import ru.gushchin.feature_favorite.domain.Weather

interface FavoriteWeatherRepository {
    suspend fun getFavoriteCityList(): Resource<Weather>
}