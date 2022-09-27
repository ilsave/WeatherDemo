package ru.gushchin.core_network.data.impl

import androidx.work.*
import ru.gushchin.core_network.data.api.WeatherApi
import ru.gushchin.core_network.data.impl.workers.WeatherInCityWorker
import ru.gushchin.core_network.di.NetworkComponent
import ru.gushchin.core_network.utils.WeatherInCityWorkerConstants.INPUT_DATA_KEY_LAT
import ru.gushchin.core_network.utils.WeatherInCityWorkerConstants.INPUT_DATA_KEY_LON
import ru.gushchin.core_network.utils.WeatherInCityWorkerConstants.TAG_WEATHER_IN_CITY
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WeatherApiImpl @Inject constructor(): WeatherApi{
    override fun getWeather(lat: Double, lon: Double): UUID {
        val weatherInCityRequest =
            OneTimeWorkRequest
                .Builder(WeatherInCityWorker::class.java)
                .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                .setInputData(
                    workDataOf(
                        INPUT_DATA_KEY_LAT to lat,
                        INPUT_DATA_KEY_LON to lon
                    )
                )
                .addTag(TAG_WEATHER_IN_CITY)
                .build()

        NetworkComponent.workManager
            ?.enqueue(weatherInCityRequest)

        return weatherInCityRequest.id
    }
}