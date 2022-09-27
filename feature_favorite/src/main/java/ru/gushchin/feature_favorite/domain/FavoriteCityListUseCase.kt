package ru.gushchin.feature_favorite.domain

import ru.gushchin.feature_favorite.presentation.SillyWeather

interface FavoriteCityListUseCase {
    suspend fun getFavoriteCityList() : Resource<List<SillyWeather?>>
}