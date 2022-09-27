package ru.gushchin.feature_detail.data.mappers

import ru.gushchin.core_local_storage.data.models.CityEntity
import ru.gushchin.core_local_storage.data.models.WeatherEntity
import ru.gushchin.core_network.data.models.WeatherDTO
import ru.gushchin.core_network.data.models.submodels.*
import ru.gushchin.feature_detail.data.models.City
import ru.gushchin.feature_detail.data.models.Weather
import ru.gushchin.feature_detail.data.models.submodels.WeatherSub

fun WeatherDTO.toWeatherEntity(city: Long): WeatherEntity =
    WeatherEntity(
        city,
        weather[0].icon,
        main.temp,
        wind.speed,
        weather[0].description
    )

fun WeatherEntity.toWeather(cityName: String): Weather =
    Weather(
        name = cityName,
        main = ru.gushchin.feature_detail.data.models.submodels.Main(
            temp = temperature
        ),
        weather = arrayListOf(
            WeatherSub(
                description = description,
                icon = icon
            )
        ),
        wind = ru.gushchin.feature_detail.data.models.submodels.Wind(
            speed = speed
        )
    )

fun WeatherDTO.toWeather(): Weather =
    Weather(
        base,
        clouds.toCloudsFeature(),
        cod,
        coord.toCoordFeature(),
        dt,
        id,
        main.toMainFeature(),
        name,
        sys.toSysFeature(),
        timezone,
        visibility,
        weather.map { it.toWeatherSubFeature() },
        wind.toWindFeature()
    )

fun City.toCityEntity() : CityEntity =
    CityEntity(
        name, latitude, longitude, isFavourite
    )

fun Clouds.toCloudsFeature(): ru.gushchin.feature_detail.data.models.submodels.Clouds =
    ru.gushchin.feature_detail.data.models.submodels.Clouds(
        all
    )

fun Coord.toCoordFeature(): ru.gushchin.feature_detail.data.models.submodels.Coord =
    ru.gushchin.feature_detail.data.models.submodels.Coord(
        lat, lon
    )

fun Main.toMainFeature(): ru.gushchin.feature_detail.data.models.submodels.Main =
    ru.gushchin.feature_detail.data.models.submodels.Main(
        feels_like, humidity, pressure, temp, temp_max, temp_min
    )

fun Sys.toSysFeature(): ru.gushchin.feature_detail.data.models.submodels.Sys =
    ru.gushchin.feature_detail.data.models.submodels.Sys(
        country, id, sunrise, sunset, type
    )

fun ru.gushchin.core_network.data.models.submodels.Weather.toWeatherSubFeature(): WeatherSub =
    WeatherSub(
        description, icon, id, main
    )

fun Wind.toWindFeature(): ru.gushchin.feature_detail.data.models.submodels.Wind =
    ru.gushchin.feature_detail.data.models.submodels.Wind(
        deg, speed
    )