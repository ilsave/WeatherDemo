package ru.gushchin.core_network.di

import dagger.Binds
import dagger.Module
import ru.gushchin.core_network.data.api.WeatherApi
import ru.gushchin.core_network.data.impl.WeatherApiImpl

@Module
interface WeatherApiModule {
    @Binds
    fun bindWeatherApi(api: WeatherApiImpl): WeatherApi
}