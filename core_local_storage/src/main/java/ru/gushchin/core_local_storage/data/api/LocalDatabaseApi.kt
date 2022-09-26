package ru.gushchin.core_local_storage.data.api

import ru.gushchin.core_local_storage.data.models.City
import ru.gushchin.core_local_storage.data.models.WeatherEntity

interface LocalDatabaseApi {
    fun saveFavouriteCity(city: City)
    fun getFavouriteCities(): List<City>?
    fun saveWeatherInCity(weather: WeatherEntity)
    fun getWeatherInCity(cityId: Long): WeatherEntity?
}