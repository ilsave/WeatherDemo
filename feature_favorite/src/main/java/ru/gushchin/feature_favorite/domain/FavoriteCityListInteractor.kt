package ru.gushchin.feature_favorite.domain

// ДОЛЖЕН ЛЕЖАТЬ В ОБЩЕМ МОДУЛЕ BASE
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}


class FavoriteCityListInteractor: FavoriteCityListUseCase {

    //INJECT DATA BASE REPO

    override suspend fun getFavoriteCityList(): Resource<Weather> {
        return Resource.Error(message = "Hardcoded string")
    }

}