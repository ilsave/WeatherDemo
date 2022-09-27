package ru.gushchin.feature_favorite.data.mappers

import ru.gushchin.core_local_storage.data.models.WeatherEntity
import ru.gushchin.feature_favorite.presentation.SillyWeather

fun WeatherEntity.toSillyWeather(nameCity: String): SillyWeather =
    SillyWeather(
        nameCity,
        temperature.toString(),
        icon,
        description
    )