package ru.gushchin.core_local_storage.data.api

import ru.gushchin.core_local_storage.data.models.CityEntity
import ru.gushchin.core_local_storage.data.models.WeatherEntity

interface LocalDatabaseApi {
    fun saveCity(city: CityEntity): Long
    fun getCities(): List<CityEntity>?
    fun getFavouriteCities(): List<CityEntity>?
    fun saveWeatherInCity(weather: WeatherEntity)
    fun getWeatherInCity(cityId: Long): WeatherEntity?
}