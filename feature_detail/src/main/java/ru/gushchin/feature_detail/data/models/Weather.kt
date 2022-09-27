package ru.gushchin.feature_detail.data.models

import ru.gushchin.feature_detail.data.models.submodels.*
import ru.gushchin.feature_detail.data.models.submodels.WeatherSub

data class Weather(
    val base: String? = null,
    val clouds: Clouds? = null,
    val cod: Int? = null,
    val coord: Coord? = null,
    val dt: Int? = null,
    val id: Int? = null,
    val main: Main? = null,
    val name: String? = null,
    val sys: Sys? = null,
    val timezone: Int? = null,
    val visibility: Int? = null,
    val weather: List<WeatherSub>? = null,
    val wind: Wind? = null
)