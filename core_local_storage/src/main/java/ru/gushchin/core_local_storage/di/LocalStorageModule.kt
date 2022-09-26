package ru.gushchin.core_local_storage.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.gushchin.core_local_storage.data.WeatherDatabase

@Module
class LocalStorageModule(private val context: Context) {

    @Provides
    fun provideLocalDatabase(): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather-database"
        ).build()
    }
}