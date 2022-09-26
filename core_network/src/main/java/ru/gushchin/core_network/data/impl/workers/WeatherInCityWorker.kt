package ru.gushchin.core_network.data.impl.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import ru.gushchin.core_network.data.service.RetrofitClientInstance
import javax.inject.Inject

class WeatherInCityWorker @Inject constructor(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters){

    override suspend fun doWork(): Result {
        val result = CoroutineScope(Dispatchers.IO).async {
            val response = RetrofitClientInstance.serviceApi
                .getWeatherData(
                    inputData.getDouble("lat", 0.0),
                    inputData.getDouble("lon", 0.0)
                )

            if (response.isSuccessful) {
                val output = Data.Builder()
                    .putString("key_weather_in_city_response", Gson().toJson(response.body()))
                    .build()
                Result.success(output)
            } else {
                Result.failure()
            }
        }
        return result.await()
    }
}