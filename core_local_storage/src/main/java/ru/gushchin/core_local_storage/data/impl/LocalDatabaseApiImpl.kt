package ru.gushchin.core_local_storage.data.impl

import ru.gushchin.core_local_storage.data.api.LocalDatabaseApi
import ru.gushchin.core_local_storage.data.models.City
import ru.gushchin.core_local_storage.data.models.WeatherEntity
import ru.gushchin.core_local_storage.di.LocalStorageComponent
import javax.inject.Inject

class LocalDatabaseApiImpl @Inject constructor(): LocalDatabaseApi{
    override fun saveFavouriteCity(city: City) {
        LocalStorageComponent.database.favouriteCityDao().saveFavouriteCity(city)
    }

    override fun getFavouriteCities(): List<City>? {
        return LocalStorageComponent.database.favouriteCityDao().getFavouriteCities()
    }

    override fun saveWeatherInCity(weather: WeatherEntity) {
        LocalStorageComponent.database.weatherDao().saveWeatherInCity(weather)
    }

    override fun getWeatherInCity(cityId: Long): WeatherEntity? {
        return LocalStorageComponent.database.weatherDao().getWeatherInCity(cityId)
    }

}