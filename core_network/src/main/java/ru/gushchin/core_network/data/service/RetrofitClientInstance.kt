package ru.gushchin.core_network.data.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gushchin.core_network.ServiceApi

object RetrofitClientInstance {
    private const val baseUrl = "https://api.openweathermap.org/"

    val serviceApi: ServiceApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServiceApi::class.java)
    }
}