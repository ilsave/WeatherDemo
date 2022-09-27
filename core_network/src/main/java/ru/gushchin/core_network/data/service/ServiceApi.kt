package ru.gushchin.core_network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gushchin.core_network.data.models.WeatherDTO

interface ServiceApi {
//https://api.openweathermap.org/data/2.5/weather?lat=36.4761&lon=-119.4432&appid=9a1ad8e040498143de6489d838221ced

    @GET("data/2.5/weather?")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = "9a1ad8e040498143de6489d838221ced"
    ): Response<WeatherDTO>

    @GET("data/2.5/weather?")
    suspend fun getWeatherCityData(
        @Query("q") lat: String = "Moscow" ,
        @Query("appid") appid: String = "9a1ad8e040498143de6489d838221ced"
    ): Response<WeatherDTO>
}