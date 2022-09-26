package ru.gushchin.feature_detail.data

import android.util.Log
import ru.gushchin.core_local_storage.data.WeatherDatabase
import ru.gushchin.core_network.RetrofitHelper
import ru.gushchin.core_network.WeatherApi
import ru.gushchin.core_network.model.WeatherDTO
import ru.gushchin.feature_detail.domain.Resource
import javax.inject.Inject

class WeatherRepositoryImpl: WeatherRepository {

    @Inject
    lateinit var weatherDatabase: WeatherDatabase

    override suspend fun getCurrentCityWeather(lat: Double, lon: Double): Resource<WeatherDTO> {
        Log.d("TESTDB", "WEATHER here")
        val response = RetrofitHelper.getInstance().create(WeatherApi::class.java).getWeatherData(36.4761,-119.4432)
        return if (response.isSuccessful) {
            Log.d("TESTDB", "WEATHER DB 1")
            weatherDatabase.weatherDao().saveWeatherInCity(
                    ru.gushchin.core_local_storage.data.models.WeatherEntity(
                        1L, 1L, "ic", 2.0, 3.0, "d"))
            Log.d("TESTDB", "WEATHER DB WORKS!!!")
            Resource.Success(data = response.body()!!)
        } else {
            Resource.Error(message = response.errorBody()?.string()!!)
        }
    }
}