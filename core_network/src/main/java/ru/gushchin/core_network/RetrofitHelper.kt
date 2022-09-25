package ru.gushchin.core_network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public object RetrofitHelper {

    val baseUrl = "https://api.openweathermap.org/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}