package ru.gushchin.feature_detail.data

import ru.gushchin.core_local_storage.data.api.LocalDatabaseApi
import ru.gushchin.core_network.data.service.RetrofitClientInstance
import ru.gushchin.core_network.ServiceApi
import ru.gushchin.core_network.data.api.WeatherApi
import ru.gushchin.core_network.data.models.WeatherDTO
import ru.gushchin.feature_detail.domain.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val localDatabaseApi: LocalDatabaseApi,
    private val weatherApi: WeatherApi
): WeatherRepository {

    override suspend fun getCurrentCityWeather(lat: Double, lon: Double): Resource<WeatherDTO> {
//        val response = RetrofitClientInstance.getInstance().create(ServiceApi::class.java).getWeatherData(36.4761,-119.4432)
//        return if (response.isSuccessful) {
//            return Resource.Success("")
//        } else {
            return Resource.Error("message = response.errorBody()?.string()!!")
//        }
    }
}