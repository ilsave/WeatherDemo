package ru.gushchin.core_network.data.api

import java.util.UUID

interface WeatherApi {
    fun getWeather(lat: Double, lon: Double): UUID
}