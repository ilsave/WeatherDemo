package ru.gushchin.core_network.data.impl

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import ru.gushchin.core_network.data.api.WeatherApi
import ru.gushchin.core_network.data.impl.workers.WeatherInCityWorker
import ru.gushchin.core_network.di.NetworkComponent
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WeatherApiImpl @Inject constructor(): WeatherApi{
    override fun getWeather() {
        val weatherInCityRequest = PeriodicWorkRequest
            .Builder(WeatherInCityWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(
                Constraints.Builder()
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .addTag("tag_weather_request")
            .build()

        NetworkComponent.workManager
            ?.enqueue(weatherInCityRequest)
    }
}