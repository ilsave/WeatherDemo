package ru.gushchin.core_local_storage.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.gushchin.core_local_storage.data.models.CityEntity

@Dao
interface CityDao {
    @Insert
    fun saveCity(city: CityEntity): Long

    @Update
    fun updateCity(city: CityEntity)

    @Query("SELECT * FROM cityentity")
    fun getCities(): List<CityEntity>?

    @Query("SELECT * FROM cityentity WHERE isFavourite = 1")
    fun getFavouriteCities(): List<CityEntity>?
}