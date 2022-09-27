package ru.gushchin.feature_favorite.data

import ru.gushchin.feature_favorite.domain.Resource
import ru.gushchin.feature_favorite.domain.Weather

class FavoriteWeatherRepositoryImpl: FavoriteWeatherRepository {
    //INJECT Api weather

    override suspend fun getFavoriteCityList(): Resource<Weather> {
        return Resource.Error(message = "haha hehe")
    }
}