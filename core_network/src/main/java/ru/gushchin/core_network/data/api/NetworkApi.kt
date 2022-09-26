package ru.gushchin.core_network.data.api

interface NetworkApi {
    fun getWeatherApi(): WeatherApi
}