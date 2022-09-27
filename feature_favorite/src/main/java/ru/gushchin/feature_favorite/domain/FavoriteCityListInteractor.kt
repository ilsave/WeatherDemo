package ru.gushchin.feature_favorite.domain

import ru.gushchin.feature_favorite.data.repository.FavoriteWeatherRepository
import ru.gushchin.feature_favorite.presentation.SillyWeather
import javax.inject.Inject

// ДОЛЖЕН ЛЕЖАТЬ В ОБЩЕМ МОДУЛЕ BASE
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}

class FavoriteCityListInteractor @Inject constructor(
    private val repository: FavoriteWeatherRepository
): FavoriteCityListUseCase {

    override suspend fun getFavoriteCityList(): Resource<List<SillyWeather?>> {
        return repository.getFavoriteCityList()
    }

}