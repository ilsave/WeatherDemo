package ru.gushchin.core_local_storage.data.impl

import ru.gushchin.core_local_storage.data.api.LocalDatabaseApi
import ru.gushchin.core_local_storage.data.models.CityEntity
import ru.gushchin.core_local_storage.data.models.WeatherEntity
import ru.gushchin.core_local_storage.di.LocalStorageComponent
import javax.inject.Inject

class LocalDatabaseApiImpl @Inject constructor() : LocalDatabaseApi {
    override fun saveCity(city: CityEntity): Long {
        val cities = LocalStorageComponent.database.cityDao().getCities()
        val cashedCity = cities?.filter { it.isTheSameCity(city) }
        if (cashedCity == null || cashedCity.isEmpty()) {
            return LocalStorageComponent.database.cityDao().saveCity(city)
        }
        if (cashedCity[0].isFavourite != city.isFavourite) {
            LocalStorageComponent.database.cityDao().updateCity(city)
        }
        return cashedCity[0].id
    }

    override fun getCities(): List<CityEntity>? {
        return LocalStorageComponent.database.cityDao().getCities()
    }

    override fun saveWeatherInCity(weather: WeatherEntity) {
        val cashedWeather =
            LocalStorageComponent.database.weatherDao().getWeatherInCity(weather.city)
        if (cashedWeather != null) {
            if (weather != cashedWeather) {
                LocalStorageComponent.database.weatherDao().updateWeatherInCity(weather)
            }
        } else {
            LocalStorageComponent.database.weatherDao().saveWeatherInCity(weather)
        }
    }

    override fun getWeatherInCity(cityId: Long): WeatherEntity? {
        return LocalStorageComponent.database.weatherDao().getWeatherInCity(cityId)
    }

}