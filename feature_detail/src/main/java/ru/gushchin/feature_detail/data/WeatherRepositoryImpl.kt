package ru.gushchin.feature_detail.data

import ru.gushchin.core_network.RetrofitHelper
import ru.gushchin.core_network.WeatherApi
import ru.gushchin.core_network.model.WeatherDTO
import ru.gushchin.feature_detail.domain.Resource

class WeatherRepositoryImpl: WeatherRepository {

    override suspend fun getCurrentCityWeather(lat: Double, lon: Double): Resource<WeatherDTO> {
        val response = RetrofitHelper.getInstance().create(WeatherApi::class.java).getWeatherData(36.4761,-119.4432)
        return if (response.isSuccessful) {
            Resource.Success(data = response.body()!!)
        } else {
            Resource.Error(message = response.errorBody()?.string()!!)
        }
    }
}