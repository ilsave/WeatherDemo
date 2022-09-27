package ru.gushchin.feature_favorite.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.gushchin.core_local_storage.data.api.LocalDatabaseApi
import ru.gushchin.feature_favorite.data.mappers.toSillyWeather
import ru.gushchin.feature_favorite.domain.Resource
import ru.gushchin.feature_favorite.presentation.SillyWeather
import javax.inject.Inject

class FavoriteWeatherRepositoryImpl @Inject constructor(
    private val localDatabaseApi: LocalDatabaseApi
) : FavoriteWeatherRepository {

    override suspend fun getFavoriteCityList(): Resource<List<SillyWeather?>> =
        suspendCancellableCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                val favoriteCities = localDatabaseApi.getFavouriteCities()
                if (favoriteCities.isNullOrEmpty()) {
                    if (continuation.isActive) {
                        continuation.resume(
                            Resource.Error("No favourite cities"), null
                        )
                    }
                }
                val citiesWithWeather = favoriteCities
                    ?.map { localDatabaseApi.getWeatherInCity(it.id)?.toSillyWeather(it.name) }
                if (citiesWithWeather.isNullOrEmpty()) {
                    if (continuation.isActive) {
                        continuation.resume(
                            Resource.Error("No cashed cities or weather"), null
                        )
                    }
                }
                if (continuation.isActive) {
                    continuation.resume(
                        Resource.Success(citiesWithWeather!!), null
                    )
                }
            }
        }
}