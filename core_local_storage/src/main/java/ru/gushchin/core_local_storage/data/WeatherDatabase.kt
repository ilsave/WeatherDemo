package ru.gushchin.core_local_storage.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.gushchin.core_local_storage.data.daos.FavouriteCityDao
import ru.gushchin.core_local_storage.data.daos.WeatherDao
import ru.gushchin.core_local_storage.data.models.City
import ru.gushchin.core_local_storage.data.models.WeatherEntity

@Database(entities = [City::class, WeatherEntity::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun favouriteCityDao(): FavouriteCityDao
    abstract fun weatherDao(): WeatherDao
}