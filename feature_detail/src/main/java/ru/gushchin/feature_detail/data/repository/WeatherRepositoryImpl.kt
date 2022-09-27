package ru.gushchin.feature_detail.data.repository

import androidx.work.WorkInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.gushchin.core_local_storage.data.api.LocalDatabaseApi
import ru.gushchin.core_local_storage.data.models.CityEntity
import ru.gushchin.core_network.data.api.WeatherApi
import ru.gushchin.core_network.di.NetworkComponent
import ru.gushchin.core_network.utils.WeatherInCityWorkerConstants.KEY_WEATHER_IN_CITY_RESPONSE
import ru.gushchin.feature_detail.data.mappers.toWeather
import ru.gushchin.feature_detail.data.mappers.toWeatherEntity
import ru.gushchin.feature_detail.data.models.Weather
import ru.gushchin.feature_detail.domain.Resource
import java.lang.reflect.Type
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val localDatabaseApi: LocalDatabaseApi,
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override suspend fun getCurrentCityWeather(
        lat: Double, lon: Double
    ): Resource<Weather> =
        suspendCancellableCoroutine { continuation ->
            val uuid = weatherApi.getWeather(lat, lon)
            CoroutineScope(Dispatchers.Main).launch {
                NetworkComponent.workManager
                    ?.getWorkInfoByIdLiveData(uuid)
                    ?.observeForever { workInfo ->
                        when (workInfo?.state) {
                            WorkInfo.State.SUCCEEDED -> {
                                val weather = getWeatherFromServerResponse(workInfo)
                                CoroutineScope(Dispatchers.IO).launch {
                                    val cityId = localDatabaseApi.saveCity(
                                        CityEntity(
                                            weather.name,
                                            lat,
                                            lon,
                                            // TODO: fix
                                            true
                                        )
                                    )
                                    localDatabaseApi.saveWeatherInCity(
                                        weather.toWeatherEntity(
                                            cityId
                                        )
                                    )
                                }
                                if (continuation.isActive) {
                                    continuation.resume(
                                        Resource.Success(weather.toWeather()),
                                        null
                                    )
                                }
                            }
                            WorkInfo.State.FAILED, WorkInfo.State.CANCELLED -> {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val cityInCash = localDatabaseApi
                                        .getCities()
                                        ?.filter {
                                            it.latitude == lat && it.longitude == lon
                                        }
                                    if (cityInCash != null && cityInCash.isNotEmpty()) {
                                        val cashedCity = cityInCash[0]
                                        val weather =
                                            localDatabaseApi.getWeatherInCity(cashedCity.id)
                                        if (weather != null) {
                                            if (continuation.isActive) {
                                                continuation.resume(
                                                    Resource.Success(weather.toWeather(cashedCity.name)),
                                                    null
                                                )
                                            }
                                        } else {
                                            if (continuation.isActive) {
                                                continuation.resume(
                                                    Resource.Error(
                                                        "No cashed weather for city",
                                                        null
                                                    ),
                                                    null
                                                )
                                            }
                                        }
                                    } else {
                                        if (continuation.isActive) {
                                            continuation.resume(
                                                Resource.Error(
                                                    "No such city in cash",
                                                    null
                                                ),
                                                null
                                            )
                                        }
                                        Resource.Error("No such city in cash", null)
                                    }

                                }
                            }
                            else -> {}
                        }
                    }
            }
        }

    private fun getWeatherFromServerResponse(workInfo: WorkInfo): ru.gushchin.core_network.data.models.WeatherDTO {
        val weatherType: Type =
            object : TypeToken<ru.gushchin.core_network.data.models.WeatherDTO>() {}.type
        return Gson().fromJson(
            workInfo.outputData.keyValueMap[KEY_WEATHER_IN_CITY_RESPONSE].toString(),
            weatherType
        )
    }

}