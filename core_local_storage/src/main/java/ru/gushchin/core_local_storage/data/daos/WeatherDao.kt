package ru.gushchin.core_local_storage.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.gushchin.core_local_storage.data.models.WeatherEntity

@Dao
interface WeatherDao {
    @Insert
    fun saveWeatherInCity(weather: WeatherEntity)

    @Update
    fun updateWeatherInCity(weather: WeatherEntity)

    @Query("SELECT * FROM weatherentity WHERE city = :cityId")
    fun getWeatherInCity(cityId: Long): WeatherEntity?
}