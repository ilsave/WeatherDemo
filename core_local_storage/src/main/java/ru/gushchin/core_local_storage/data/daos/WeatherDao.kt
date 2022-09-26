package ru.gushchin.core_local_storage.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.gushchin.core_local_storage.data.models.WeatherEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeatherInCity(weather: WeatherEntity)

    @Query("SELECT * FROM weatherentity WHERE city = :cityId")
    fun getWeatherInCity(cityId: Long): WeatherEntity?
}