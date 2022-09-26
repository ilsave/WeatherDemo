package ru.gushchin.core_local_storage.daos
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import ru.gushchin.core_local_storage.models.City
//
//@Dao
//interface FavouriteCityDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun saveFavouriteCity(city: City)
//
//    @Query("SELECT * FROM city")
//    fun getFavouriteCities(): List<City>
//}