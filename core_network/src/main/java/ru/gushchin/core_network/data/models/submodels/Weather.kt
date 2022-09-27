package ru.gushchin.core_network.data.models.submodels

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)