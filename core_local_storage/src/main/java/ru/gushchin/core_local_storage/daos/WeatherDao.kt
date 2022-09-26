package ru.gushchin.core_local_storage.daos
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import ru.gushchin.core_local_storage.models.City
//import ru.gushchin.core_local_storage.models.Weather
//
//@Dao
//interface WeatherDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun saveWeatherInCity(weather: Weather)
//
//    @Query("SELECT * FROM weather WHERE city = :cityId")
//    fun getWeatherInCity(cityId: Long): Weather
//}