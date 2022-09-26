package ru.gushchin.core_network.di

import dagger.Component
import ru.gushchin.core_network.data.api.NetworkApi
import javax.inject.Singleton

@Singleton
@Component(modules = [WeatherApiModule::class])
interface WeatherApiComponent: NetworkApi