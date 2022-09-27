package ru.gushchin.core_local_storage.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.gushchin.core_local_storage.data.daos.CityDao
import ru.gushchin.core_local_storage.data.daos.WeatherDao
import ru.gushchin.core_local_storage.data.models.CityEntity
import ru.gushchin.core_local_storage.data.models.WeatherEntity

@Database(entities = [CityEntity::class, WeatherEntity::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun weatherDao(): WeatherDao
}