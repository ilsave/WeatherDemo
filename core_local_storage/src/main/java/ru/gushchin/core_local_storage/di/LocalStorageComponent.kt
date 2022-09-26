package ru.gushchin.core_local_storage.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import dagger.Component
import dagger.internal.Preconditions
import ru.gushchin.core_local_storage.data.WeatherDatabase

@Component(dependencies = [Context::class])
abstract class LocalStorageComponent {
    companion object {
        @Volatile
        var instance: LocalStorageComponent? = null

        @Volatile
        lateinit var database: WeatherDatabase

        fun initAndGet(context: Context): LocalStorageComponent? {
            if (instance == null) {
                synchronized(LocalStorageComponent::class) {
                    database = Room.databaseBuilder(
                        context,
                        WeatherDatabase::class.java,
                        "weather-database"
                    ).build()
                    instance = DaggerLocalStorageComponent.builder()
                        .context(context)
                        .build()
                }
            }
            return instance
        }

        fun get(): LocalStorageComponent {
            return Preconditions.checkNotNull(instance,
            "LocalStorageComponent is not initialized yet. Call init first.")!!
        }
    }
}