package ru.gushchin.feature_favorite.domain

interface FavoriteCityListUseCase {
    suspend fun getFavoriteCityList() : Resource<Weather>
}